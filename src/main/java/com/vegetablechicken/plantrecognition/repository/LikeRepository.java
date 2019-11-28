package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.primaryKey.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, LikePK> {
    List<Like> findByUseridAndPid(String userid, String pid);
}
