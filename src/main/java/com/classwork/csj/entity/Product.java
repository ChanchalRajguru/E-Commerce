package com.classwork.csj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ApiModel(description = "Product information")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, message = "Name should be a minimum of two characters.")
    @ApiModelProperty(notes = "Name should be a minimum of two characters.")
    private String name;
    private Double amount;

    @Min(value=1, message = "Quantity should be a minimum of one.")
    @ApiModelProperty(notes = "Quantity of products")
    private int quantity;
    private int minimumQuantity;
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime create_date = LocalDateTime.now();
}
