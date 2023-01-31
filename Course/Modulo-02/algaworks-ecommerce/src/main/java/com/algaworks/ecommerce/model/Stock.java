package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "product_id")
    private Integer productId;
    private Integer quantity;
}
