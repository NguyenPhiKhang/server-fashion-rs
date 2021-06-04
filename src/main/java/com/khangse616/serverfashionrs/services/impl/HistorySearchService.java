package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.repositories.HistorySearchRepository;
import com.khangse616.serverfashionrs.services.IHistorySearchService;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class HistorySearchService implements IHistorySearchService {
    @Autowired
    private HistorySearchRepository historySearchRepository;

    @Autowired
    private IUserService userService;

    @Override
    public List<HistorySearch> getAllHistorySearchByUser(int userId) {
        return historySearchRepository.getHistorySearchByUserIdOrderByTimeSearchDesc(userId);
    }

    @Override
    public void createOrUpdateHistorySearch(int userId, String keyword) {
        String keywordLowerCase = keyword.toLowerCase(Locale.ROOT);

        HistorySearch historySearch = historySearchRepository.findHistorySearchByUserIdAndKeyword(userId, keywordLowerCase);
        if(historySearch!=null){
            historySearch.setKeyword(keywordLowerCase);
            historySearch.setTimeSearch(new Timestamp(System.currentTimeMillis()));
        }else{
            historySearch = new HistorySearch();
            historySearch.setKeyword(keywordLowerCase);
            historySearch.setUser(userService.getUserById(userId));
            historySearch.setTimeSearch(new Timestamp(System.currentTimeMillis()));

            HistorySearch historySearch1 = historySearchRepository.findTop1HistorySearchByUserId(userId);
            int id;
            Random rd = new Random();

            do {
                id = 100 + rd.nextInt(6000001);
            } while (historySearchRepository.existsById(id));

            historySearch.setId(id);
        }

        historySearchRepository.save(historySearch);
    }

    @Override
    public void removeHistorySearch(int id) {
        historySearchRepository.deleteById(id);
    }

    @Override
    public void removeAllHistorySearch(int userId) {
        historySearchRepository.deleteAllByUserId(userId);
    }
}
