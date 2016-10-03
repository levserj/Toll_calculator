package com.levserj.toll.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Levchynskyi on 01.10.2016.
 */
public class User {
    private String id;
    private String Email;
    private String firstName;
    private String lastName;
    private List<Trip> trips;

    public User() {
    }

    public User(String Email) {
        this.Email = Email;
    }

    public User(String id, String Email) {
        this.id = id;
        this.Email = Email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public List<Trip> getTrips() {
        if (trips == null){
            return new ArrayList<>();
        }
        return trips;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return (getEmail() != null ?
                getEmail().equals(user.getEmail()) :
                user.getEmail() == null);

    }

    @Override
    public int hashCode() {
        return getEmail() != null ? getEmail().hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id=%s, E-mail='%s']",
                id, Email);
    }
}
