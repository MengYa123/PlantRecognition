package com.vegetablechicken.plantrecognition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thought")
public class Thought {
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Id
    @Column(name = "thought_id",length = 50)
    private String tid;
    @Column(name = "user_id",length = 50)
    private String userid;
    private String content;
    @Column(name = "likes_num")
    private int likes;

}
