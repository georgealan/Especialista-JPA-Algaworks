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
@Table(name = "product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners({GeneralListener.class})
public class Product {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "creation_date", updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "last_update_date", insertable = false)
    private LocalDateTime lastUpdateDate;

    @OneToMany(mappedBy = "product")
    private List<OrderedItem> orderedItems;
    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
    private String name;
    private String description;
    private BigDecimal price;
    @OneToOne(mappedBy = "product")
    private Stock stock;

    @ElementCollection
    @CollectionTable(name = "product_tag",
        joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private List<String> tags;
}
