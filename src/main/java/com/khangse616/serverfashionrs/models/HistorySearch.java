package com.khangse616.serverfashionrs.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "history_search")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HistorySearch {

    @Id
    private int id;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "time_search")
    private Timestamp timeSearch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public HistorySearch() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Timestamp getTimeSearch() {
        return timeSearch;
    }

    public void setTimeSearch(Timestamp timeSearch) {
        this.timeSearch = timeSearch;
    }
}
