package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,String> {
    User findByUserid(String userId);
}
