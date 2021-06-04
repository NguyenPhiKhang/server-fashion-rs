package com.khangse616.serverfashionrs.controllers.impl;


import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.controllers.IHistorySearchController;
import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.services.IHistorySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
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
    public String removeHistorySearch(int userId, String hsType) {
        try {
            if (hsType.equals("all"))
                historySearchService.removeAllHistorySearch(userId);
            else {
                if (StringUtil.checkStringIsNumeric(hsType))
                    historySearchService.removeHistorySearch(Integer.parseInt(hsType));
                else return "Xoá không thành công.";
            }
            return "Xoá thành công";
        }catch (Exception exception){
            return "Xoá không thành công.";
        }
    }
}
