package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.HistorySearch;

import java.util.List;

public interface IHistorySearchService {
    List<HistorySearch> getAllHistorySearchByUser(int userId);
    void createOrUpdateHistorySearch(int userId, String keyword);
    void removeHistorySearch(int id);
}
