package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "shipping")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Shipping implements Serializable {
    @Id
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Order.class, mappedBy = "shipping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders;

    public Shipping() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
