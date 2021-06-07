package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.models.dto.HotSearchDTO;

import java.util.*;
import java.util.stream.Collectors;

public interface IHistorySearchService {
    List<HistorySearch> getAllHistorySearchByUser(int userId);
    void createOrUpdateHistorySearch(int userId, String keyword);
    void removeHistorySearch(int id);
    void removeAllHistorySearch(int userId);
    void autoHistorySearch();
    Set<String> recommendSearch(String keyword);
    List<HotSearchDTO> getTopSearch();
}
