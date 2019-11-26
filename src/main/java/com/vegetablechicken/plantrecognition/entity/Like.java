package com.vegetablechicken.plantrecognition.entity;

import com.vegetablechicken.plantrecognition.primaryKey.LikePK;

import javax.persistence.*;

@Entity
@Table(name = "user_like")
@IdClass(LikePK.class)
public class Like {

    @Id
    @Column(name = "user_id",length = 50)
    private String userid;
    @Id
    @Column(name = "plant_id",length = 50)
    private String pid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
