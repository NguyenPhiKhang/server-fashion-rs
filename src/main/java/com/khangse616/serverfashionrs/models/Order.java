package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable {
    @Id
    private int id;

    //    user_id
//    address_id
    @Column(name = "sub_total")
    private BigDecimal subTotal;
    @Column(name = "shipping_fee")
    private BigDecimal shippingFree;
    @Column(name = "grand_total")
    private BigDecimal grandTotal;
    @Column(name = "discount")
    private BigDecimal discount;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
//    shipping_unit
//            payment_method
//    status
}
