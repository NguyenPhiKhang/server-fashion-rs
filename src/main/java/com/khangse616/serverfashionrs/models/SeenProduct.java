package com.khangse616.serverfashionrs.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "seen_products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SeenProduct {
    @EmbeddedId
    private ProductUserKey id;

    @Column(name ="last_time")
    private Timestamp lastTime;
    @Column(name ="count")
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public SeenProduct() {
    }

    public ProductUserKey getId() {
        return id;
    }

    public void setId(ProductUserKey id) {
        this.id = id;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
