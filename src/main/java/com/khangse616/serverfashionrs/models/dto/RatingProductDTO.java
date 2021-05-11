package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RatingProductDTO {

    private CountRatingProductDTO totalCount;
    private List<RatingDTO> data;

    public RatingProductDTO() {
    }

    @JsonProperty("total_count")
    public CountRatingProductDTO getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(CountRatingProductDTO totalCount) {
        this.totalCount = totalCount;
    }

    @JsonProperty("list-rating")
    public List<RatingDTO> getData() {
        return data;
    }

    public void setData(List<RatingDTO> data) {
        this.data = data;
    }
}
