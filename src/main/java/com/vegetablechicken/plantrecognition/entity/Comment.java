package com.vegetablechicken.plantrecognition.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
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

    @Id
    @Column(name = "thought_id",length = 50)
    private String tid;
    @Column(name = "user_id",length = 50)
    private String userid;
    private String content;
}
