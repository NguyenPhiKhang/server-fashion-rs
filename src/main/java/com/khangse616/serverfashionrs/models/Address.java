package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address {
    @Id
    private int id;
    @Column(name = "specific_address")
    private String specificAddress;
    @Column(name = "name")
    private String name;
    @Column(name = "number_phone")
    private String numberPhone;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updateAt;
    @Column(name = "default_is")
    private boolean defaultIs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @OneToMany(targetEntity = Order.class, mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders;

    public Address() {
    }

    public Address(String specificAddress, String name, String numberPhone, boolean defaultIs, Timestamp createdAt, Timestamp updateAt) {
        this.specificAddress = specificAddress;
        this.name = name;
        this.numberPhone = numberPhone;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.defaultIs = defaultIs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecificAddress() {
        return specificAddress;
    }

    public void setSpecificAddress(String specificAddress) {
        this.specificAddress = specificAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isDefaultIs() {
        return defaultIs;
    }

    public void setDefaultIs(boolean defaultIs) {
        this.defaultIs = defaultIs;
    }
}
