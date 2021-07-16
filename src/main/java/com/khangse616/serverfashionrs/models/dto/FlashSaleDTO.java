package com.khangse616.serverfashionrs.models.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class FlashSaleDTO {
    private int id;
    private Time startTime;
    private Time endTime;
    private Date date;
    private String status;

    private List<FlashSaleCardDTO> flashSaleCards;

    public FlashSaleDTO() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FlashSaleCardDTO> getFlashSaleCards() {
        return flashSaleCards;
    }

    public void setFlashSaleCards(List<FlashSaleCardDTO> flashSaleCards) {
        this.flashSaleCards = flashSaleCards;
    }
}
