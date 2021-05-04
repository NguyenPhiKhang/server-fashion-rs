package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IRatingController;
import com.khangse616.serverfashionrs.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RatingController implements IRatingController {
    @Autowired
    private IRatingService ratingService;

    @PostMapping("/rating/auto-rating")
    @Override
    public ResponseEntity<String> autoRating(){
        ratingService.autoRating();
        return ResponseEntity.ok().body("done");
    }
}