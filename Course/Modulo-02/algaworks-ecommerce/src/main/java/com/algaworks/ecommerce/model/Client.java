package com.algaworks.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ClientGender gender;
}