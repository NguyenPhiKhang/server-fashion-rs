package com.khangse616.serverfashionrs.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

////@NamedStoredProcedureQuery(name = "countDetailRatings",
////        procedureName = "count_detail_ratings", parameters = {
////        @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Integer.class)},)
//
//@NamedStoredProcedureQuery(
//        name = "Rating.countDetailRatings",
//        procedureName = "count_detail_ratings",
//        parameters = {
//        @StoredProcedureParameter(mode = ParameterMode.IN, name = "productId", type = Integer.class)},
//        resultSetMappings = {"set1","set2","countDetailRatingsSetMapping"}
//)
//
////@SqlResultSetMapping(
////        name = "countDetailRatingsSetMapping",
////        classes = {
////                @ConstructorResult(
////                        columns = {
////                                @ColumnResult(name = "totalAll"),
////                                @ColumnResult(name = "totalImage"),
////                                @ColumnResult(name = "totalStar1"),
////                                @ColumnResult(name = "totalStar2"),
////                                @ColumnResult(name = "totalStar3"),
////                                @ColumnResult(name = "totalStar4"),
////                                @ColumnResult(name = "totalStar5"),
////                        },
////                        targetClass = CountRatingProductDTO.class
////                )
////        }
////)
//
//@SqlResultSetMapping(
//        name = "countDetailRatingsSetMapping",
//        entities = {
//                @EntityResult(
//                        entityClass = CountRatingProductDTO.class,
//                        fields = {
//                                @FieldResult(name="totalAll", column = "totalAll"),
//                                @FieldResult(name="totalImage", column = "totalImage"),
//                                @FieldResult(name="totalStar1", column = "totalStar1"),
//                                @FieldResult(name="totalStar2", column = "totalStar2"),
//                                @FieldResult(name="totalStar3", column = "totalStar3"),
//                                @FieldResult(name="totalStar4", column = "totalStar4"),
//                                @FieldResult(name="totalStar5", column = "totalStar5"),
//                        }
//                )
//        }
//)
public class CountRatingProductDTO {
    private int totalAll;
    private int totalImage;
    private int totalStar1;
    private int totalStar2;
    private int totalStar3;
    private int totalStar4;
    private int totalStar5;

    public CountRatingProductDTO() {
    }

    public CountRatingProductDTO(int totalAll, int totalImage, int totalStar1, int totalStar2, int totalStar3, int totalStar4, int totalStar5) {
        this.totalAll = totalAll;
        this.totalImage = totalImage;
        this.totalStar1 = totalStar1;
        this.totalStar2 = totalStar2;
        this.totalStar3 = totalStar3;
        this.totalStar4 = totalStar4;
        this.totalStar5 = totalStar5;
    }

    public CountRatingProductDTO(int totalAll, int totalStar1, int totalStar2, int totalStar3, int totalStar4, int totalStar5) {
        this.totalAll = totalAll;
        this.totalStar1 = totalStar1;
        this.totalStar2 = totalStar2;
        this.totalStar3 = totalStar3;
        this.totalStar4 = totalStar4;
        this.totalStar5 = totalStar5;
    }

    @JsonProperty("total_all")
    public int getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(int totalAll) {
        this.totalAll = totalAll;
    }

    @JsonProperty("total_image")
    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
    }

    @JsonProperty("total_star1")
    public int getTotalStar1() {
        return totalStar1;
    }

    public void setTotalStar1(int totalStar1) {
        this.totalStar1 = totalStar1;
    }

    @JsonProperty("total_star2")
    public int getTotalStar2() {
        return totalStar2;
    }

    public void setTotalStar2(int totalStar2) {
        this.totalStar2 = totalStar2;
    }

    @JsonProperty("total_star3")
    public int getTotalStar3() {
        return totalStar3;
    }

    public void setTotalStar3(int totalStar3) {
        this.totalStar3 = totalStar3;
    }

    @JsonProperty("total_star4")
    public int getTotalStar4() {
        return totalStar4;
    }

    public void setTotalStar4(int totalStar4) {
        this.totalStar4 = totalStar4;
    }

    @JsonProperty("total_star5")
    public int getTotalStar5() {
        return totalStar5;
    }

    public void setTotalStar5(int totalStar5) {
        this.totalStar5 = totalStar5;
    }

}
