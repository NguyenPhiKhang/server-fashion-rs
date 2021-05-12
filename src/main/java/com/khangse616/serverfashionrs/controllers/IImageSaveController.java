package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/default")
public interface IImageSaveController {
    @PostMapping("/images/upload-url")
    ResponseEntity<ResponseMessage<ImageDataSave>> uploadFile(@RequestBody ImageData imageData);

    @PostMapping("/images/upload-multi-url")
    ResponseEntity<ResponseMessage<List<ImageDataSave>>> uploadFiles();
}
