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
@Table(name = "plant_image")
public class Plantimage {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long piid;
    private String pic;
    private String name;

}
