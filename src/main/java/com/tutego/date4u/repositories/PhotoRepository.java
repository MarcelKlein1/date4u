package com.tutego.date4u.repositories;

import com.tutego.date4u.entities.Photo;
import com.tutego.date4u.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PhotoRepository extends CrudRepository<Photo, Long> {
    @Query("SELECT p FROM Photo p WHERE p.profile = ?1 AND p.isProfilePhoto IS TRUE ")
    public Photo findByProfilePhoto(Profile profile);

    @Query("SELECT p FROM Photo p WHERE p.profile =?1")
    public List<Photo> findByProfile(Profile profile);

    @Query("SELECT p FROM Photo p WHERE p.name =?1")
    public Photo findPhotoByName(String name);
}
