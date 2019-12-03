package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.entity.Plantimage;
import com.vegetablechicken.plantrecognition.primaryKey.LikePK;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlantimageRepository extends PagingAndSortingRepository<Plantimage,String> {
}
