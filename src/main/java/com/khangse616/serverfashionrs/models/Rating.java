package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    private int id;
    @Column(name = "comment")
    private String comment;
    @Column(name = "star")
    private int star;
    @Column(name = "time_created")
    private Timestamp timeCreated;
    @Column(name = "time_updated")
    private Timestamp timeUpdated;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToMany(targetEntity = ImageData.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "rating_image",
            joinColumns =
            @JoinColumn(name = "rating_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<ImageData> dataImages = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "product_attribute_id")
    @JsonIgnore
    private Product productAttribute;

    public Rating(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
    }

    public Timestamp getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(Timestamp timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<ImageData> getDataImages() {
        return dataImages;
    }

    public void setImageDataImages(Set<ImageData> dataImages) {
        this.dataImages = dataImages;
    }

    public void setDataImages(Set<ImageData> dataImages) {
        this.dataImages = dataImages;
    }

    public Product getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(Product productAttribute) {
        this.productAttribute = productAttribute;
    }
}

