package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "payment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment implements Serializable {
    @Id
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(targetEntity = Order.class, mappedBy = "paymentMethod", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders;

    public Payment() {
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
