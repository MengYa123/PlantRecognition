package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlantRepository extends PagingAndSortingRepository<Plant,String> {
    public Plant findByPid(long pid);
    public List<Plant> findByKind(String kind);
}
