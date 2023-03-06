package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Transient
    private String firstName;
    @Enumerated(EnumType.STRING)
    private ClientGender gender;
    @OneToMany(mappedBy = "client")
    private List<PurchaseOrder> purchaseOrders;
    @PostLoad
    public void configFirstName() {
        if (name != null && !name.isBlank()) {
            int index = name.indexOf(" ");
            if (index > -1) {
                firstName = name.substring(0, index);
            }
        }
    }
}
