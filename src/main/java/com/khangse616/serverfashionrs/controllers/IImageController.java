package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface IImageController {
    @GetMapping("/image/{id}")
    ResponseEntity<byte[]> getFile(@PathVariable String id);
}
