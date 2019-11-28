package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,String> {
}
