package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.dto.RatingProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IRatingController {
    @PostMapping("/rating/auto-rating")
    ResponseEntity<String> autoRating();

    @PostMapping("/rating/auto-insert-attribute")
    ResponseEntity<String> autoInsertAttribute();

    @GetMapping("/rating/{productId}")
    ResponseEntity<RatingProductDTO> getRatingByProduct(@PathVariable("productId") int id, @RequestParam(value = "select", defaultValue = "all") String select, @RequestParam(value = "p", defaultValue = "1") int page);
}
