package com.classwork.csj.repository;

import com.classwork.csj.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    //@Procedure(procedureName = "findAllProducts")
    @Query(nativeQuery = true, value = "call findAllProducts")
    List<Product> fetchAll();

    @Query(nativeQuery = true, value = "call findProductsWhereNameLike(?1)")
    Long fetchProductCountByName(String name);

    void deleteProductsByAmount(Double amount);

    Page<Product> findAll(Pageable pageable);

    Long countProductsByNameLike(String name);

    List<Product> findByNameIgnoreCaseLike(String name);

    Product findByIdOrName(Long id, String name);
}
