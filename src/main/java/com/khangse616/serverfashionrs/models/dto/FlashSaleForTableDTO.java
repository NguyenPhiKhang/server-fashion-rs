package com.khangse616.serverfashionrs.models.dto;

import java.sql.Date;
import java.sql.Time;

public class FlashSaleForTableDTO {
    private int id;
    private Time startTime;
    private Time endTime;
    private Date date;
    private int status;
    private int totalProduct;
    private int totalSale;

    public FlashSaleForTableDTO() {
    }

    public FlashSaleForTableDTO(int id, Time startTime, Time endTime, Date date, int totalProduct, int totalSale) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.totalProduct = totalProduct;
        this.totalSale = totalSale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public int getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }
}
