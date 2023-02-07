package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "invoice")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Invoice {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private PurchaseOrder purchaseOrder;
    private String xml;
    @Column(name = "issue_date")
    private Date issueDate;
}
