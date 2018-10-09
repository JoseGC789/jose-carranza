package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.entity.Person;

import java.util.Objects;

public class Credential {
    private String token;
    private Person person;

    public Credential() {
    }

    public Credential(String token, Person person) {
        this.token = token;
        this.person = person;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credential that = (Credential) o;
        return Objects.equals(person.getUsername(), that.person.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(person);
    }
}
