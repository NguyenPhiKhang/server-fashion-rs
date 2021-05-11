package com.khangse616.serverfashionrs.models.dto;

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

    public int getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(int totalAll) {
        this.totalAll = totalAll;
    }

    public int getTotalImage() {
        return totalImage;
    }

    public void setTotalImage(int totalImage) {
        this.totalImage = totalImage;
    }

    public int getTotalStar1() {
        return totalStar1;
    }

    public void setTotalStar1(int totalStar1) {
        this.totalStar1 = totalStar1;
    }

    public int getTotalStar2() {
        return totalStar2;
    }

    public void setTotalStar2(int totalStar2) {
        this.totalStar2 = totalStar2;
    }

    public int getTotalStar3() {
        return totalStar3;
    }

    public void setTotalStar3(int totalStar3) {
        this.totalStar3 = totalStar3;
    }

    public int getTotalStar4() {
        return totalStar4;
    }

    public void setTotalStar4(int totalStar4) {
        this.totalStar4 = totalStar4;
    }

    public int getTotalStar5() {
        return totalStar5;
    }

    public void setTotalStar5(int totalStar5) {
        this.totalStar5 = totalStar5;
    }
}
