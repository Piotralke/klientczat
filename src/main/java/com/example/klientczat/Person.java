
package com.example.klientczat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.hateoas.Links;

public class Person {

    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("lastName")
    @Expose
    public String lastName;
    @SerializedName("login")
    @Expose
    public String login;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("name")
    @Expose
    public String name;

    public Person(){};

    public Person(String firstName, String lastName, String login, String password) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.login=login;
        this.password=password;
        this.name = firstName + " " + lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + this.id + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName
                + '\'' + ", login='" + this.login + '\'' + '}';
    }
}
