package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchaseOrder {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "conclusion_date")
    private LocalDateTime conclusionDate;
    @Column(name = "invoice_id")
    private Integer invoiceId;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    @Embedded
    private OrderDeliveryAddress orderDeliveryAddress;
}
