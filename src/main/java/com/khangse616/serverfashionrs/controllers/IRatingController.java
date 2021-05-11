package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.RatingProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface IRatingController {
    @PostMapping("/rating/auto-rating")
    ResponseEntity<String> autoRating();

    @GetMapping("/rating/{productId}")
    ResponseEntity<RatingProductDTO> getRatingByProduct(@PathVariable("productId") int id, @RequestParam(value = "select", defaultValue = "all") String select, @RequestParam(value = "p", defaultValue = "1") int page);
}
