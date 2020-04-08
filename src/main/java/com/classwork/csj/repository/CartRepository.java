package com.classwork.csj.repository;

import com.classwork.csj.entity.Cart;
import com.classwork.csj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
    List<Cart> findByExpired(boolean expired);
    Cart findCartByUser(User user);
}
