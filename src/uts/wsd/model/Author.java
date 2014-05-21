package uts.wsd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@SuppressWarnings("serial")

public class Author implements Serializable {
    private int id;
    private String email;
    private String password;
    private String name;
    private String biography;
    private Date birth;

    public Author(String email, String password, String name, String biography, Date birth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.biography = biography;
        this.birth = birth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean login(String email, String password) {
        return (this.email.equals(email) && this.password.equals(password));
    }
}