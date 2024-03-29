package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ordered_item")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderedItem {

    @EmbeddedId
    private OrderedItemId id;

    @MapsId("purchaseOrderId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;

    @MapsId("productId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    private Integer quantity;
}
