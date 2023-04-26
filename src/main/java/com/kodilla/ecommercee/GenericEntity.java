package com.kodilla.ecommercee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String theValue;

    public GenericEntity() {
    }

    public String getTheValue() {
        return theValue;
    }

    public Long getId() {

        return id;
    }

    public GenericEntity(String theValue) {

        this.theValue = theValue;
    }
}
