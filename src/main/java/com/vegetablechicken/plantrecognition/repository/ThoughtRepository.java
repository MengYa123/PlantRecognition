package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Thought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ThoughtRepository extends PagingAndSortingRepository<Thought,String> {
}
