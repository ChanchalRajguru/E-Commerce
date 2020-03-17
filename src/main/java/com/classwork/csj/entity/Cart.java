package com.classwork.csj.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "expiry status of cart")
    private boolean expired;

    @ApiModelProperty(notes = "owner of cart")
    @OneToOne
    private User user;

    @ApiModelProperty(notes = "list of item(s) added to cart")
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Transient
    public Double getTotalCartPrice() {
        double sum = 0D;
        List<CartItem> cartItems = getItems();

        for (CartItem ci : cartItems) {
            sum += ci.getTotalPrice();
        }

        return sum;
    }

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
