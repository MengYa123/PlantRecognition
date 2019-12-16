package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Plant;
import com.vegetablechicken.plantrecognition.entity.Recommend;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommendRepository extends PagingAndSortingRepository<Recommend,String> {
    public Recommend findByEmail(String email);
}
