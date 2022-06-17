package com.tutego.date4u.repositories;

import com.tutego.date4u.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.nickname = :name")
    Optional<Profile> findProfileByNickname(String name);

    @Query("SELECT p FROM Profile p WHERE p.nickname LIKE %:name%")
    List<Profile> findProfilesByContainingName(String name);

    @Query("SELECT p FROM Profile p WHERE p.hornlength BETWEEN :min AND :max")
    List<Profile> findProfilesByHornlengthBetween(short min, short max);

    @Query("SELECT p FROM Profile p WHERE p.gender=?1")
    List<Profile> findProfileByGender(byte gender);

    @Query("SELECT p FROM Profile p WHERE p.gender=?1 AND p.hornlength BETWEEN ?2 AND ?3")
    List<Profile> findProfileByGenderAndHornlength(byte gender, short min, short max);

}

