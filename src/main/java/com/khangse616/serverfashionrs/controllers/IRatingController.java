package com.khangse616.serverfashionrs.controllers;

import org.springframework.http.ResponseEntity;

public interface IRatingController {
    ResponseEntity<String> autoRating();
}
