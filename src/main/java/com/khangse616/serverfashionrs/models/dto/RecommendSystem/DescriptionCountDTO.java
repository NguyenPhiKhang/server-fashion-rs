package com.khangse616.serverfashionrs.models.dto.RecommendSystem;

public class DescriptionCountDTO {
    private String descOrName;
    private int count;

    public DescriptionCountDTO(String descOrName, int count) {
        this.descOrName = descOrName;
        this.count = count;
    }

    public String getDescOrName() {
        return descOrName;
    }

    public void setDescOrName(String descOrName) {
        this.descOrName = descOrName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
