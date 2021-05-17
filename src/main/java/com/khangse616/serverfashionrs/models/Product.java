package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "sku")
    private String sku;
    @Column(name = "description")
    @JsonIgnore
    private String description;
    @Column(name = "short_description")
    @JsonIgnore
    private String shortDescription;
    @Column(name = "highlight")
    private String highlight;
    @Column(name = "type_id")
    private String typeId;
    @Column(name = "visibility")
    private boolean visibility;
    @Column(name = "is_active")
    private boolean active;
    @Column(name = "promotion_percent")
    private float promotionPercent;
    @Column(name = "order_count")
    private int orderCount;
    @Column(name = "is_free_ship")
    private boolean freeShip;
    @Column(name = "material")
    private String material;
    @Column(name = "style")
    private String style;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "suitable_season")
    private String suitableSeason;
    @Column(name = "made_in")
    private String madeIn;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "liked")
    private boolean liked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_star_id")
    @JsonIgnore
    private RatingStar ratingStar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @JsonIgnore
    private Brand brand;

    @ManyToMany(targetEntity = OptionProductVarchar.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "catalog_product_varchar",
            joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<OptionProductVarchar> optionProductVarchars = new HashSet<>();

    @ManyToMany(targetEntity = OptionProductInteger.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "catalog_product_int",
            joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<OptionProductInteger> optionProductIntegers = new HashSet<>();

    @ManyToMany(targetEntity = OptionProductDecimal.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "catalog_product_decimal",
            joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "option_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<OptionProductDecimal> optionProductDecimals = new HashSet<>();

    @ManyToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinTable(
            name = "catalog_product_link",
            joinColumns =
            @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "link_product_id", referencedColumnName = "id"))
    @JsonIgnore
    private Set<Product> productLinks = new HashSet<>();

    @ManyToMany(targetEntity = Product.class, mappedBy = "productLinks", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    @OneToMany(targetEntity = Rating.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @BatchSize(size = 2)
    private Set<Rating> ratings;

    @OneToMany(targetEntity = Rating.class, mappedBy = "productAttribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Rating> ratingsAttribute;

//    @OneToMany(targetEntity = CartItem.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Cart> carts;

//    @OneToMany(targetEntity = Favorite.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Favorite> favoriteProducts;

    public Product() {
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

    public float getPromotionPercent() {
        return promotionPercent;
    }

    public void setPromotionPercent(float promotionPercent) {
        this.promotionPercent = promotionPercent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isFreeShip() {
        return freeShip;
    }

    public void setFreeShip(boolean freeShip) {
        this.freeShip = freeShip;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSuitableSeason() {
        return suitableSeason;
    }

    public void setSuitableSeason(String suitableSeason) {
        this.suitableSeason = suitableSeason;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<OptionProductVarchar> getOptionProductVarchars() {
        return optionProductVarchars;
    }

    public void setOptionProductVarchars(Set<OptionProductVarchar> optionProductVarchars) {
        this.optionProductVarchars = optionProductVarchars;
    }

    public Set<OptionProductInteger> getOptionProductIntegers() {
        return optionProductIntegers;
    }

    public void setOptionProductIntegers(Set<OptionProductInteger> optionProductIntegers) {
        this.optionProductIntegers = optionProductIntegers;
    }

    public Set<OptionProductDecimal> getOptionProductDecimals() {
        return optionProductDecimals;
    }

    public void setOptionProductDecimals(Set<OptionProductDecimal> optionProductDecimals) {
        this.optionProductDecimals = optionProductDecimals;
    }

    public Set<Product> getProductLinks() {
        return productLinks;
    }

    public void setProductLinks(Set<Product> productLinks) {
        this.productLinks = productLinks;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public RatingStar getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(RatingStar ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Rating> getRatingsAttribute() {
        return ratingsAttribute;
    }

    public void setRatingsAttribute(Set<Rating> ratingsAttribute) {
        this.ratingsAttribute = ratingsAttribute;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
