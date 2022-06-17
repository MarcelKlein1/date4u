package com.tutego.date4u.entities;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
@Table(name = "unicorn")
public class Unicorn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne
    @JoinColumn(name = "profile_fk")
    private Profile profile;

    public Unicorn(String email, String password, Profile profile) {
        this.profile = profile;
        setEmail(email);
        setPassword(password);
    }

    public Unicorn() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}

