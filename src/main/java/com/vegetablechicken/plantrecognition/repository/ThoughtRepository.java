package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.entity.Thought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface ThoughtRepository extends PagingAndSortingRepository<Thought,String> {
    public List<Thought> findByEmailOrderByTid(String email);
    public Thought findByTid(long tid);
    public void deleteByTid(long tid);

}
