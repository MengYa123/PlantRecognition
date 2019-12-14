package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.primaryKey.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LikeRepository extends PagingAndSortingRepository<Like, LikePK> {
    List<Like> findByUseridAndPid(String userid, long pid);
    void deleteByUseridAndPid(String userid, long pid);
    List<Like> findByUserid(String userid);
}
