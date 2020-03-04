package com.classwork.csj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter

public class userType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //the usertype
    private String name;
}
