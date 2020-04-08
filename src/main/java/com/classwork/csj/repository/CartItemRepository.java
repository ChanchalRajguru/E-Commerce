package com.classwork.csj.repository;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteByCartId(Long cartId);
    List<CartItem> findCartItemsByCart(Cart cart);
}
