package com.classwork.csj.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @Size(min = 2)
    @ApiModelProperty(notes = "Name should be a minimum of two characters.")
    private String name;

    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    @Min(value=1, message = "Quantity should be a minimum of one.")
    @ApiModelProperty(notes = "Quantity of products")
    private int quantity;
    private int minimumQuantity;
    private String description;
    private LocalDateTime create_date = LocalDateTime.now();
}
