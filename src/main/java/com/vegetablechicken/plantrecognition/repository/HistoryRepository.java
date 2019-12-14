package com.vegetablechicken.plantrecognition.repository;

import com.vegetablechicken.plantrecognition.entity.Comment;
import com.vegetablechicken.plantrecognition.entity.History;
import com.vegetablechicken.plantrecognition.entity.Like;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface HistoryRepository  extends PagingAndSortingRepository<History,String> {
    public List<History> findByUseridOrderByHidDesc(String userid);
    public History findByHid(long hid);
    public void  deleteByHid(long hid);
}
