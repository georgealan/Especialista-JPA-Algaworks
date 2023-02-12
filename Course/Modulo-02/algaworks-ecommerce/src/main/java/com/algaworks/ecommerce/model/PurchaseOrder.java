package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listeners.GeneralListener;
import com.algaworks.ecommerce.listeners.GenerateInvoiceListener;
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
@EntityListeners({GenerateInvoiceListener.class, GeneralListener.class})
public class PurchaseOrder {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderedItem> orderedItems;
    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;
    @Column(name = "last_update_date", insertable = false)
    private LocalDateTime lastUpdateDate;
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    @OneToOne(mappedBy = "purchaseOrder")
    private PaymentCard payment;
    @OneToOne(mappedBy = "purchaseOrder")
    private Invoice invoice;
    @Embedded
    private OrderDeliveryAddress orderDeliveryAddress;

    public boolean isPaid() {
        return StatusOrder.PAID.equals(status);
    }

//    @PrePersist
//    @PreUpdate
    public void calculateTotal() {
        if (orderedItems != null) {
            total = orderedItems.stream()
                    .map(OrderedItem::getProductPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    /* \\\\\\ Other pre-processors \\\\\\
    Each class can only have one callback annotation per class, if a class have
    the same callback annotation more than once, will throw an exception. Because of that
    use only one per class, this is explicit in this class for example, in calculateTotal()
    method I would use the same callback annotations again, I commented both, because they
    were causing errors. Use only one per class.
     */

    @PrePersist
    public void whenPersist() {
        creationDate = LocalDateTime.now();
        calculateTotal(); //
    }

    @PreUpdate
    public void whenUpdate() {
        lastUpdateDate = LocalDateTime.now();
        calculateTotal(); //
    }

    @PostPersist
    public void afterPersist() {
        System.out.println("After persist Order.");
    }

    @PostUpdate
    public void afterUpdate() {
        System.out.println("After update Order");
    }

    @PreRemove
    public void beforeRemove() {
        System.out.println("Before remove Order");
    }

    @PostRemove
    public void afterRemove() {
        System.out.println("After remove Order");
    }

    @PostLoad
    public void afterLoadEntity() {
        System.out.println("After load entity Order");
    }
}
