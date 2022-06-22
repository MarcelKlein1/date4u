package com.tutego.date4u.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "profile")
@Access(AccessType.FIELD)
public class Profile {

    public static final int FEE = 1;
    public static final int MAA = 2;
    private static final long serialVersionUID = 8325886845899123834L;
    @OneToMany(
            mappedBy = "profile",
            fetch = FetchType.EAGER)
    private List<Photo> photos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "hornlength")
    private short hornlength;

    @Column(name = "gender")
    private byte gender;

    @Column(name = "attracted_to_gender")
    private Byte attractedToGender;

    @Column(name = "description")
    private String description;

    @Column(name = "lastseen")
    private LocalDateTime lastseen;

    public Profile() {
    }

    public Profile(String nickname, LocalDate birthdate, int hornlength,
                   int gender, Integer attractedToGender, String description,
                   LocalDateTime lastseen) {
        setNickname(nickname);
        setBirthdate(birthdate);
        setHornlength(hornlength);
        setGender(gender);
        setAttractedToGender(attractedToGender);
        setDescription(description);
        setLastseen(lastseen);
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getHornlength() {
        return hornlength;
    }

    public void setHornlength(int hornlength) {
        this.hornlength = (short) hornlength;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = (byte) gender;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public Profile add(Photo photo) {
        photos.add(photo);
        return this;
    }

    public @Nullable Integer getAttractedToGender() {
        return attractedToGender == null ? null : attractedToGender.intValue();
    }

    public void setAttractedToGender(@Nullable Integer attractedToGender) {
        this.attractedToGender = attractedToGender == null ? null : attractedToGender.byteValue();
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
        this.lastseen = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Profile profile
                && nickname.equals(profile.nickname);
    }

    @Override
    public int hashCode() {
        return nickname.hashCode();
    }

    @Override
    public String toString() {
        return "Profile[id=%d]".formatted(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

}
