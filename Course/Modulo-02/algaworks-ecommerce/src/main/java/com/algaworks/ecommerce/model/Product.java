package com.algaworks.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
}
