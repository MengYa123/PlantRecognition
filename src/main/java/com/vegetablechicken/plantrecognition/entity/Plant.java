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
@Table(name = "plant")
public class Plant {
    @Id
    @Column(name = "plant_id",length = 50)
    private String pid;
    private String pic;
    private String kind;
    private String name;
    private String detail;


}
