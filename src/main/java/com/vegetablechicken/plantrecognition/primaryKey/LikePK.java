package com.vegetablechicken.plantrecognition.primaryKey;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikePK implements Serializable {
    private String email;
    private long pid;
}
