package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Comment;
import com.vegetablechicken.plantrecognition.entity.History;
import com.vegetablechicken.plantrecognition.entity.Like;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HistoryRepository  extends PagingAndSortingRepository<History,String> {
   List<History> findByEmailOrderByHidDesc(String email);
   History findByHid(long hid);
   void  deleteByHid(long hid);
    List<History> findByEmailAndKind(String email, String kind);
}
