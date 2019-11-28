package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Thought;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThoughtRepository extends JpaRepository<Thought,String> {
}
