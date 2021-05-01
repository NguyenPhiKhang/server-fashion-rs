package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "attributes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Attribute {
    @Id
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "label")
    private String label;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OptionProductVarchar> optionProductVarchars;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OptionProductInteger> optionProductIntegers;

    @OneToMany(mappedBy = "attribute", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<OptionProductDecimal> optionProductDecimals;

    public Attribute(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
