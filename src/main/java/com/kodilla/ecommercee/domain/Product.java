package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity (name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;

    //TODO: when Group entity implemented
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "groupId")
//    private Group group;

}
