package com.algaworks.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderedItemId implements Serializable {
    /*
    This class is used for map composite key used in the class OrderedItem class.
     */
    @Id
    @EqualsAndHashCode.Include
    @Column(name = "order_id")
    private Integer purchaseOrderId; // Composite Keys

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "product_id")
    private Integer productId; // Composite Keys
}
