package com.khangse616.serverfashionrs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
public interface IRatingController {
    @PostMapping("/rating/auto-rating")
    ResponseEntity<String> autoRating();
}
