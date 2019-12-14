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
@Table(name = "plant")
public class Plant {
    @Id
    @Column(name = "plant_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long pid;
    private String pic;
    private String kind;
    private String name;
    private String detail;



}
