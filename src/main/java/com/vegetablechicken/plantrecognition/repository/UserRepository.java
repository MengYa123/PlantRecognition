package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
