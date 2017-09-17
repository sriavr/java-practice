package sridharpractice;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class EqualsTest {

    @Test
    public void testEqualsPrimitives(){
        int num1 = 3, num2 = 3;
        Assert.assertTrue(num1 == num2);
    }

    @Test
    public void testEqualsStrings(){
        String str1 = "krishna";
        String str2 = "krishna";

        System.out.println("String does not have equals method it simply compares two strings");
        Assert.assertTrue(str1 == str2);
        Assert.assertTrue(str1.equals(str2));

        str1 = new String("Krishna");
        str2 = new String("Krishna");

        Assert.assertFalse(str1 == str2);
        Assert.assertTrue(str1.equals(str2));
    }

    @Test
    public void testNotEqualsStringBuilder(){
        StringBuilder str1 = new StringBuilder("Vasudeva");
        StringBuilder str2 = new StringBuilder("Vasudeva");

        Assert.assertFalse(str1.equals(str2));
        Assert.assertFalse(str1 == str2);
    }

    @Test
    public void testNullEqualsNull(){
        Assert.assertTrue(null == null);
    }

    @Test
    public void testNarasimhaEqualsNarasimha(){
        NarasimhaAvatar avatar1 = new NarasimhaAvatar(1, 0, "Narasimha");
        NarasimhaAvatar avatar2 = new NarasimhaAvatar(1, 0, "Narasimha");
        Assert.assertFalse(avatar1 == avatar2);
        Assert.assertTrue(avatar1.equals(avatar2));
    }

    @Test
    public void testEqualsObjects(){
        Devotee one;
        Devotee two;

        one = new Devotee(100, "Prahlada", "Varada", 1486);
        two = new Devotee(100, "Prahlada", "Varada", 1486);

        System.out.println("one.hashCode() = " + one.hashCode());
        System.out.println("two.hashCode() = " + two.hashCode());

        System.out.println("one = " + one + "\n two = "+ two);
        Assert.assertTrue(one.equals(two));
    }


    @Test
    public void testNotEqualsInSetWithAutoHashCode(){
        Devotee one;
        Devotee two;

        one = new Devotee(100, "Prahlada", "Varada", 1486);
        two = new Devotee(100, "Prahlada", "Varada", 1486);

        System.out.println("one.hashCode() = " + one.hashCode());
        System.out.println("two.hashCode() = " + two.hashCode());

        Set<Devotee> devoteeSet = new HashSet<Devotee>();
        devoteeSet.add(one);
        devoteeSet.add(two);

        //This is false because the hashCode is generated automatically by JVM
        Assert.assertFalse(devoteeSet.size() == 1);
    }

    @Test
    public void testEqualsInSetWithProperHashCode(){
        DevoteeWithProperHashCode one;
        DevoteeWithProperHashCode two;

        one = new DevoteeWithProperHashCode(100, "Prahlada", "Varada", 1486);
        two = new DevoteeWithProperHashCode(100, "Prahlada", "Varada", 1486);

        System.out.println("one.hashCode() = " + one.hashCode());
        System.out.println("two.hashCode() = " + two.hashCode());

        Set<Devotee> devoteeSet = new HashSet<Devotee>();
        devoteeSet.add(one);
        devoteeSet.add(two);

        Assert.assertTrue(one.equals(two));
        Assert.assertTrue(one.hashCode() == two.hashCode());
        System.out.println("devoteeSet.size() = " + devoteeSet.size());
        Assert.assertTrue(devoteeSet.size() == 1);
    }

}


class Devotee{
    public int devoteeId;

    public String firstName, lastName;

    public int yearInitiated;

    public Devotee(int devoteeId, String firstName, String lastName, int yearInitiated) {
        this.devoteeId = devoteeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearInitiated = yearInitiated;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

class DevoteeWithProperHashCode extends Devotee{
    public DevoteeWithProperHashCode(int devoteeId, String firstName, String lastName, int yearInitiated) {
        super(devoteeId, firstName, lastName, yearInitiated);
    }

    @Override
    public int hashCode() {
        return devoteeId + 7*  firstName.hashCode() + 13* lastName.hashCode() + 17* yearInitiated;
    }
}

class Avatar {
    private int id;
    private int age;
    private String name;

    public Avatar(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Avatar))
            return false;

        Avatar otherAvatar = (Avatar) obj;
        return this.id == otherAvatar.id;
    }
}

class NarasimhaAvatar extends Avatar{

    public NarasimhaAvatar(int id, int age, String name) {
        super(id, age, name);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}