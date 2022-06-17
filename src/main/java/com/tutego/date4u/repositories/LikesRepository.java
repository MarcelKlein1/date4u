package com.tutego.date4u.repositories;

import com.tutego.date4u.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LikesRepository extends CrudRepository<Profile, Profile> {

}
