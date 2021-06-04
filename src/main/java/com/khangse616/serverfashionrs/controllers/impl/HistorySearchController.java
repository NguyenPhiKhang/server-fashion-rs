package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.controllers.IHistorySearchController;
import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.services.IHistorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HistorySearchController implements IHistorySearchController {
    @Autowired
    private IHistorySearchService historySearchService;

    @Override
    public String createOrUpdateHistorySearch(int userId, String keyword) {
        historySearchService.createOrUpdateHistorySearch(userId, keyword);

        return "Thành công";
    }

    @Override
    public List<HistorySearch> getListHistorySearchByUser(int userId) {
        return historySearchService.getAllHistorySearchByUser(userId);
    }

    @Override
    public String removeHistorySearch(int id) {
         historySearchService.removeHistorySearch(id);
         return "Dã xoá khỏi lịch sử tìm kiếm";
    }
}
