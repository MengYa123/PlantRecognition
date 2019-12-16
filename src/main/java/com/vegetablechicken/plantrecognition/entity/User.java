package com.vegetablechicken.plantrecognition.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
public class User {
    @Id
    @Column(name = "user_id",length = 50)
    private String email;
    private String name;
    private String password;
    private String avatar;
    private String background;
    private String signature;
}
