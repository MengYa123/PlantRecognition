package com.vegetablechicken.plantrecognition.entity;

import com.vegetablechicken.plantrecognition.primaryKey.LikePK;
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
@Table(name = "user_like")
@IdClass(LikePK.class)
public class Like {

    @Id
    @Column(name = "user_id",length = 50)
    private String userid;
    @Id
    @Column(name = "plant_id")
    private long pid;


}
