package com.levserj.toll.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
public class UserTest {

    @Test
    public void usersWithSameEmailAreEqual(){
        User vasya = new User("vasya@gmail.com");
        User clone = new User("vasya@gmail.com");
        assertThat(vasya.equals(clone), is(true));
    }

    @Test
    public void usersWithDifferentEmailAreNotEqual(){
        User vasya = new User("vasya@gmail.com");
        User petya = new User("petya@gmail.com");
        assertThat(vasya.equals(petya), is(false));
    }

    @Test
    public void usersWithSameEmailHaveSameHashcode(){
        User vasya = new User("vasya@gmail.com");
        User clone = new User("vasya@gmail.com");
        assertThat(vasya.hashCode() == clone.hashCode(), is(true));
    }

    @Test
    public void usersWithDifferentEmailHaveDifferentHashcode(){
        User vasya = new User("vasya@gmail.com");
        User petya = new User("petya@gmail.com");
        assertThat(vasya.hashCode() == petya.hashCode(), is(false));
    }

    @Test
    public void usersWithNoEmailProcessedCorrectly(){
        User vasya = new User("vasya@gmail.com");
        User petya = new User();
        assertThat(vasya.equals(petya), is(false));
        assertThat(vasya.hashCode() == petya.hashCode(), is(false));
        assertThat(petya.hashCode() == 0, is(true));
    }

    @Test
    public void usersComparedCorrectlyWithNull(){
        User vasya = new User("vasya@gmail.com");
        User petya = null;
        assertThat(vasya.equals(petya), is(false));
    }
}
