package com.khangse616.serverfashionrs.controllers;


import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.models.dto.HotSearchDTO;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RequestMapping("/default")
public interface IHistorySearchController {
    @PostMapping("/{userId}/history-search")
    String createOrUpdateHistorySearch(@PathVariable("userId") int userId, @RequestParam(value = "keyword") String keyword);

    @GetMapping("/{userId}/get-history-search")
    List<HistorySearch> getListHistorySearchByUser(@PathVariable("userId") int userId);

    @DeleteMapping("/{userId}/remove-history-search")
    @Modifying
    @Transactional
    String removeHistorySearch(@PathVariable("userId") int userId, @RequestParam("hs") String hsType);

    @PostMapping("/history-search/auto")
    String autoHistorySearch();

    @GetMapping("/search/recommend-search")
    ResponseEntity<Set<String>> getRecommendSearch(@RequestParam("keyword") String keyword);

    @GetMapping("/search/hot-search-item")
    ResponseEntity<List<HotSearchDTO>> getHotSearchItem(@RequestParam(value = "p", defaultValue = "1") int page);

    @GetMapping("/search/hot-search-text")
    ResponseEntity<List<String>> getHotSearchText();
}
