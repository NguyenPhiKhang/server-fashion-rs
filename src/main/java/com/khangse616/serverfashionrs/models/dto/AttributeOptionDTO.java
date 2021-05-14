package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;

import java.util.List;

public class AttributeOptionDTO {
    private int productId;
    private List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar;
    private List<OptionProductDTO> optionProductDTOList;

    public AttributeOptionDTO() {
    }

    @JsonProperty("product-id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @JsonProperty("attributes")
    public List<AttributeDTO<OptionProductVarchar>> getListAttributeVarchar() {
        return listAttributeVarchar;
    }

    public void setListAttributeVarchar(List<AttributeDTO<OptionProductVarchar>> listAttributeVarchar) {
        this.listAttributeVarchar = listAttributeVarchar;
    }

    @JsonProperty("product-options")
    public List<OptionProductDTO> getOptionProductDTOList() {
        return optionProductDTOList;
    }

    public void setOptionProductDTOList(List<OptionProductDTO> optionProductDTOList) {
        this.optionProductDTOList = optionProductDTOList;
    }
}
