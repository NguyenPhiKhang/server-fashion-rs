package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.OptionProductDecimal;
import com.khangse616.serverfashionrs.models.OptionProductInteger;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;

import java.util.ArrayList;
import java.util.List;

public class OptionProductDTO {
    private List<OptionProductVarchar> optionProductVarcharList;
    private OptionProductDecimal price;
    private OptionProductInteger quantity;

    public OptionProductDTO() {
        optionProductVarcharList = new ArrayList<>();
    }

    @JsonProperty(value = "option")
    public List<OptionProductVarchar> getOptionProductVarcharList() {
        return optionProductVarcharList;
    }

    public void setOptionProductVarcharList(List<OptionProductVarchar> optionProductVarcharList) {
        this.optionProductVarcharList = optionProductVarcharList;
    }

    public OptionProductDecimal getPrice() {
        return price;
    }

    public void setPrice(OptionProductDecimal price) {
        this.price = price;
    }

    public OptionProductInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(OptionProductInteger quantity) {
        this.quantity = quantity;
    }
}
