package com.khangse616.serverfashionrs.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class ProductUserKey implements Serializable {
    @Column(name = "product_id")
    int productId;

    @Column(name = "user_id")
    int userId;

    public ProductUserKey() {
    }

    public ProductUserKey(int productId, int userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
