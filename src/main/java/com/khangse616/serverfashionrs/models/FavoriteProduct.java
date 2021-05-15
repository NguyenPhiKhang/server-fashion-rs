package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "favorite-products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FavoriteProduct {
    @EmbeddedId
    private ProductUserKey id;

    @Column(name = "like")
    private boolean like;
    @Column(name ="time_seen")
    private Timestamp timeSeen;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    public FavoriteProduct() {
    }

    public ProductUserKey getId() {
        return id;
    }

    public void setId(ProductUserKey id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Timestamp getTimeSeen() {
        return timeSeen;
    }

    public void setTimeSeen(Timestamp timeSeen) {
        this.timeSeen = timeSeen;
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
