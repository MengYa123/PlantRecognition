package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlantRepository extends PagingAndSortingRepository<Plant,String> {
}
