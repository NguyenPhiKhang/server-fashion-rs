package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000", "https://adminfashion-shop.azurewebsites.net"})
public interface IImageSaveController {
    @PostMapping("/images/upload-url")
    ResponseEntity<ResponseMessage<ImageDataSave>> uploadFile(@RequestBody ImageData imageData);

    @PostMapping("/images/upload-multi-url")
    ResponseEntity<ResponseMessage<List<ImageDataSave>>> uploadFiles();

    @GetMapping("/image-save/{id}")
    ResponseEntity<byte[]> getFile(@PathVariable String id);
}
