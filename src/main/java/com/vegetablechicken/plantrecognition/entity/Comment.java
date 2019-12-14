package com.vegetablechicken.plantrecognition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long cid;
    @Column(name = "thought_id")
    private long tid;
    @Column(name = "user_id",length = 50)
    private String userid;
    private String content;




}
