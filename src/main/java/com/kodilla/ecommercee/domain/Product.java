package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity (name = "products")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @NotNull
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;

    /* temporarily. TODO - when entity Group implemented
    @NotNull
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    */
}
