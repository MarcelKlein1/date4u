package com.tutego.date4u.repositories;

import com.tutego.date4u.entities.Like;
import com.tutego.date4u.entities.LikeId;
import com.tutego.date4u.entities.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface LikeRepository extends CrudRepository<Like, LikeId> {
    @Query("SELECT l FROM Like l WHERE l.likerFk =?1")
    public List<Like> findLikesByLikerFk(Profile profile);

    @Query("SELECT l FROM Like l WHERE l.likeeFk =?1")
    public List<Like> findLikesByLikeeFk(Profile profile);

    @Query("SELECT l FROM Like l WHERE l.likerFk =?1 AND l.likeeFk =?2")
    public Like findLikesByLikerFkAndLikeeFk(Profile liker, Profile likee);

    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.likerFk =?1 AND l.likeeFk =?2")
    public void deleteLikeByLikerFkAndLikeeFk(Profile liker, Profile likee);
}