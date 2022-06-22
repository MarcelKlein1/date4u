package com.tutego.date4u.entities;

import javax.persistence.*;

@Entity
@Table(name = "LIKES")
public class Like {
    @EmbeddedId
    private LikeId id = new LikeId();

    @MapsId("likerFk")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LIKER_FK", nullable = false)
    private Profile likerFk;

    @MapsId("likeeFk")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LIKEE_FK", nullable = false)
    private Profile likeeFk;

    public Like() {
    }

    public Like(Profile likerFk, Profile likeeFk) {
        this.likerFk = likerFk;
        this.likeeFk = likeeFk;
    }

    public LikeId getId() {
        return id;
    }

    public void setId(LikeId id) {
        this.id = id;
    }

    public Profile getLikerFk() {
        return likerFk;
    }

    public void setLikerFk(Profile likerFk) {
        this.likerFk = likerFk;
    }

    public Profile getLikeeFk() {
        return likeeFk;
    }

    public void setLikeeFk(Profile likeeFk) {
        this.likeeFk = likeeFk;
    }

}