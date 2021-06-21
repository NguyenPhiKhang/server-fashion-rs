package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IImageController;
import com.khangse616.serverfashionrs.exceptions.ResourceNotFoundException;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import com.khangse616.serverfashionrs.repositories.ImageDataRepository;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ImageController implements IImageController {
    @Autowired
    private IImageDataService imageDataService;

    @Override
    public ResponseEntity<byte[]> getFile(String id) {
        ImageData image = imageDataService.getFile(id).orElseThrow(() -> new ResourceNotFoundException("khong tim thay image id: " + id));

        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getType())).body(image.getData());
    }
}
