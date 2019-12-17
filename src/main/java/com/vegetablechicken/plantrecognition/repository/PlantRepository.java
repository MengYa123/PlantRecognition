package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Like;
import com.vegetablechicken.plantrecognition.entity.Plant;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlantRepository extends PagingAndSortingRepository<Plant,String> {
    public Plant findByPid(long pid);
    public List<Plant> findByKind(String kind);
    public List<Plant> findByName(String name);

   // @Query(value="select * from plant where name like '%科' ")
   // public List<Plant> findByNameLike();

   // @Query(value = "select * from plant where kind =?1 and pic is not null")
   // List<Plant> findBykindewithpic(String kind);
}
