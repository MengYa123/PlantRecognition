package com.vegetablechicken.plantrecognition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "thought_id",length = 50)
    private String tid;
    @Column(name = "user_id",length = 50)
    private String userid;
    private String content;




}
