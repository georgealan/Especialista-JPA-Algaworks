package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_card")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentCard {
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "purchaseOrder_id")
    private Integer id;
    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "purchaseOrder_id")
    private PurchaseOrder purchaseOrder;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    private String number;
}
