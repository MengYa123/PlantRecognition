package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Star;
import com.vegetablechicken.plantrecognition.primaryKey.StarPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StarRepository extends JpaRepository<Star, StarPK> {
    List<Star> findAllByUserIdFirst(String userIdFirst);
    List<Star> findAllByUserIdSecond(String userIdSecond);
    Star findByUserIdFirstAndAndUserIdSecond(String userIdFirst, String userIdSecond);
}
