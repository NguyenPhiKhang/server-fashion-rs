package com.khangse616.serverfashionrs.models.dto.RecommendSystem;

public class CosineSimilarityProductBySearchDTO {
    private int id;
    private double value;

    public CosineSimilarityProductBySearchDTO() {
    }

    public CosineSimilarityProductBySearchDTO(int id, double value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
