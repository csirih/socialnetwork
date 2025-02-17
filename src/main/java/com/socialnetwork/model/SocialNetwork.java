package com.socialnetwork.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;



@Data
public class SocialNetwork {
    private Integer connectionId;

    private String userId1;
    private String userId2;

    
    private Integer weight;



}
