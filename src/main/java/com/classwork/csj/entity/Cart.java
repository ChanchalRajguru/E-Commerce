package com.classwork.csj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private String name;
    private int quantity;
    private boolean expired;

    @OneToMany
    private List<Product> product;

    @OneToOne
    //@JoinColumn(name = "userId")
    private User user;
}
