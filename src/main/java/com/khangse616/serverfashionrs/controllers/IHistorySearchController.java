package com.khangse616.serverfashionrs.controllers;


import com.khangse616.serverfashionrs.models.HistorySearch;
import com.khangse616.serverfashionrs.models.dto.ProductItemDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

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
}
