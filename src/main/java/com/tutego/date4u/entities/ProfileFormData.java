package com.tutego.date4u.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProfileFormData {

    private long id;

    private LocalDate birthdate;

    private String nickname;
    private int hornlength;
    private int gender;

    private Integer attractedToGender;

    private String description;

    private LocalDateTime lastseen;

    public ProfileFormData() {
    }

    public ProfileFormData(long id, LocalDate birthdate, String nickname, short hornlength,
                           short gender, Integer attractedToGender, String description,
                           LocalDateTime lastseen) {
        this.id = id;
        this.birthdate = birthdate;
        this.nickname = nickname;
        this.hornlength = hornlength;
        this.gender = gender;
        this.attractedToGender = attractedToGender;
        this.description = description;
        this.lastseen = lastseen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHornlength() {
        return hornlength;
    }

    public void setHornlength(int hornlength) {
        this.hornlength = hornlength;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Integer getAttractedToGender() {
        return attractedToGender;
    }

    public void setAttractedToGender(int attractedToGender) {
        this.attractedToGender = attractedToGender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastseen() {
        return lastseen;
    }

    public void setLastseen(LocalDateTime lastseen) {
        this.lastseen = lastseen;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

}
