package com.tutego.date4u.entities;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "photo")
@EntityListeners(AuditingEntityListener.class)
@Access(AccessType.FIELD)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profile_fk")
    private Profile profile;

    private String name;

    @Column(name = "is_profile_photo")
    private boolean isProfilePhoto;

    @CreatedDate
    private LocalDateTime created;

    public Photo() {

    }

    // Setter/Getter

    @Override
    public String toString() {
        return "Photo[" + id + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public boolean isProfilePhoto() {
        return isProfilePhoto;
    }

    public void setProfilePhoto(boolean profilePhoto) {
        isProfilePhoto = profilePhoto;
    }
}