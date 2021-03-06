package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "sex")
    private String sex;
    @Column(name = "active")
    private boolean active;
    @Column(name = "time_created")
    private Timestamp timeCreated;
    @Column(name = "time_updated")
    private Timestamp timeUpdated;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="image_avatar", unique= true, referencedColumnName = "id")
    private ImageData imageAvatar;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonIgnore
    private Account account;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "user")
    @JsonIgnore
    private Cart cart;

    @OneToMany(targetEntity = Favorite.class, mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Favorite> favoriteProducts;

    @OneToMany(targetEntity = Address.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Address> addresses;

    @OneToMany(targetEntity = HistorySearch.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<HistorySearch> historySearches;

    @OneToMany(targetEntity = Order.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Order> orders;

    public User(){}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ImageData getImageAvatar() {
        return imageAvatar;
    }

    public void setImageAvatar(ImageData imageAvatar) {
        this.imageAvatar = imageAvatar;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<Favorite> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(Set<Favorite> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<HistorySearch> getHistorySearches() {
        return historySearches;
    }

    public void setHistorySearches(Set<HistorySearch> historySearches) {
        this.historySearches = historySearches;
    }
}
