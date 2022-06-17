package com.tutego.date4u.repositories;

import com.tutego.date4u.entities.Profile;
import com.tutego.date4u.entities.Unicorn;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnicornRepository extends CrudRepository<Unicorn, Long> {
    @Query("SELECT u FROM Unicorn u WHERE u.email IS NOT NULL")
    Optional<Unicorn> findIfEmail();

    @Query("SELECT u FROM Unicorn u WHERE u.email = ?1")
    public Unicorn findByEmail(String email);

    @Query("SELECT u FROM Unicorn u WHERE u.profile = ?1")
    public Unicorn findByProfile(Profile profile);
}
