package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "purchase_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PurchaseOrder {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderedItem> orderedItems;
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
