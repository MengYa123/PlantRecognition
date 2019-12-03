package com.vegetablechicken.plantrecognition.repository;

        import com.vegetablechicken.plantrecognition.entity.Comment;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.repository.PagingAndSortingRepository;


        import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment,String> {
    public List<Comment> findByTid(String tid);
}
