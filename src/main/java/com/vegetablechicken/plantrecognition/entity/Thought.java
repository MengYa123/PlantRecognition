package com.vegetablechicken.plantrecognition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "thought")
public class Thought {
    @Id
    @Column(name = "thought_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long tid;
    @Column(name = "user_id",length = 50)
    private String userid;
    private String content;
    private String pic;
    @Column(name = "likes_num")
    private int likes;





}
