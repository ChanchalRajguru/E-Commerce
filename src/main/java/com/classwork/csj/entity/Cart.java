package com.classwork.csj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private List<CartItem> cartItems = new ArrayList<>();


    @Transient
    public Double getTotalCartPrice() {
        double sum = 0D;
        List<CartItem> cartItems = getCartItems();

        for (CartItem ci : cartItems) {
            sum += ci.getTotalPrice();
        }

        return sum;
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
        item.setCart(this);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
        item.setCart(null);
    }
}
