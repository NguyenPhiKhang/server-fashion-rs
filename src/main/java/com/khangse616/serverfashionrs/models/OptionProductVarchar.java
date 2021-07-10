package com.khangse616.serverfashionrs.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khangse616.serverfashionrs.Utils.StringUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "option_product_varchar")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OptionProductVarchar implements Serializable, Comparable {

    @Id
    private int id;
    @Column(name = "value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
//    @JsonIgnore
    private Attribute attribute = new Attribute();

    @ManyToMany(targetEntity = Product.class, mappedBy = "optionProductVarchars", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Product> products = new HashSet<>();

    public OptionProductVarchar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public int compareTo(Object o) {
        String compareValue = ((OptionProductVarchar) o).getValue();

        if(StringUtil.checkStringIsNumeric(compareValue))
            return StringUtil.stringCompare(value, compareValue);
        else return StringUtil.stringCompare(compareValue, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionProductVarchar)) return false;
        OptionProductVarchar that = (OptionProductVarchar) o;
        return getId() == that.getId() && Objects.equals(getValue(), that.getValue()) && Objects.equals(getAttribute(), that.getAttribute()) && Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getValue(), getAttribute(), getProducts());
    }
}
