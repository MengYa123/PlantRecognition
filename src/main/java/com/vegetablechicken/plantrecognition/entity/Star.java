package com.vegetablechicken.plantrecognition.entity;

import com.vegetablechicken.plantrecognition.primaryKey.StarPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "star")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StarPK.class)
public class Star {
    @Id
    @Column(length = 50)
    private String userIdFirst;

    @Id
    @Column(length = 50)
    private String userIdSecond;
}
