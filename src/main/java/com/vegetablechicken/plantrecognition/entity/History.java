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
@Table(name = "history")
public class History {
    @Id
    @Column(name = "history_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long hid;
    @Column(name = "user_id",length = 50)
    private String email;
    private long pid;
    private String name;
    private String kind;
    private String pic;
}
