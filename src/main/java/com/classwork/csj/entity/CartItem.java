package com.classwork.csj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne
    private Product product;

    @Transient
    public Double getTotalPrice() {
        return getProduct().getAmount() * getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        return id != null && id.equals(((CartItem) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
