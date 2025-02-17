package com.socialnetwork.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class User {

    private String userId;
    private String name;
    private String email;

}
