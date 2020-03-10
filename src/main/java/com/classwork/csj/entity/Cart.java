package com.classwork.csj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;
}
