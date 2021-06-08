package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.RecommendSystemUtil;
import com.khangse616.serverfashionrs.mappers.impl.HotSearchDTOMapper;
import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.dto.HotSearchDTO;
import com.khangse616.serverfashionrs.repositories.HistorySearchRepository;
import com.khangse616.serverfashionrs.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistorySearchService implements IHistorySearchService {
    @Autowired
    private HistorySearchRepository historySearchRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IImageDataService imageDataService;

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

    @Override
    public void autoHistorySearch() {
        List<String> listNameCategories = categoryService.getAllNameCategories();
        List<String> newWord = new ArrayList<>();
        listNameCategories.forEach(c-> {
            newWord.addAll(Arrays.asList(c.split(" - ")));
        });
        List<Integer> listIdUser = userService.getListIdUser();

        Random rd = new Random();
        int listUserSize = listIdUser.size();
        int listNameCategoriesSize = newWord.size();

        for(int i = 0; i < listUserSize - 100; i++){
            int numSearch = 1 + rd.nextInt(12);

            for(int j = 0; j<numSearch; j++){
                int idxSearch = rd.nextInt(listNameCategoriesSize);
                createOrUpdateHistorySearch(listIdUser.get(i), newWord.get(idxSearch));
            }
        }
    }

    @Override
    public Set<String> recommendSearch(String keyword) {
        Set<String> listSearchLike = historySearchRepository.getTopSearchLike(keyword);
        if(listSearchLike.size()!=0)
            return listSearchLike;

        List<String> listSearch = historySearchRepository.getAllSearch();

        return RecommendSystemUtil.calcCosineSimilaritySearch(keyword, listSearch).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey).collect(Collectors.toSet());
    }

    @Override
    public List<HotSearchDTO> getTopSearchItem(int page, int size) {
//        return historySearchRepository.getTopSearch();
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<String> pageTopSearches = historySearchRepository.getTopSearch(pageable);
        List<String> topSearches = pageTopSearches.getContent();
        System.out.println(topSearches);
        List<Product> listProduct = productService.getAllProductVisibility();

        return RecommendSystemUtil.calcCosineSimilaritySearch(topSearches, listProduct).entrySet().stream().map(v->  new HotSearchDTOMapper().mapRow(v, imageDataService)).collect(Collectors.toList());
    }

    @Override
    public List<String> getTopSearch(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<String> pageTopSearch = historySearchRepository.getTopSearch(pageable);
        System.out.println(pageTopSearch.getContent());
        return pageTopSearch.getContent();
    }
}
