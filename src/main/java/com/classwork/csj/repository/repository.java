package com.classwork.csj.repository;

import com.classwork.csj.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<Product, Long> {
}
