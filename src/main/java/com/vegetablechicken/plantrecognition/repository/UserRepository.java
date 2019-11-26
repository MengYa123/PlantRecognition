package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
