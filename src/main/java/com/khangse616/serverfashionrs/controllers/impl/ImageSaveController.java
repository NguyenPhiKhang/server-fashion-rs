package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.controllers.IImageSaveController;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import com.khangse616.serverfashionrs.services.IImageDataSaveService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ImageSaveController implements IImageSaveController {
    @Autowired
    private IImageDataSaveService imageService;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public ResponseEntity<ResponseMessage<ImageDataSave>> uploadFile(ImageData imageData) {
        MultipartFile file = ImageUtil.createMultipartFileFromUrl(imageData);
        return ImageUtil.uploadImage(imageService, file);
    }

    @SneakyThrows
    @Override
    public ResponseEntity<ResponseMessage<List<ImageDataSave>>> uploadFiles() {
        List<ImageData> imageDataList = imageDataService.getAllImages();

        List<MultipartFile> files = ImageUtil.createMultipartFileFromUrls(imageDataList);

        return ImageUtil.uploadImages(imageService, files);
    }
}
