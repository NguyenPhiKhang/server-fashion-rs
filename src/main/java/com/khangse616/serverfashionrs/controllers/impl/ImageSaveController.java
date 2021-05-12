package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.controllers.IImageSaveController;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageSaveController implements IImageSaveController {

    @Override
    public ResponseEntity<ResponseMessage<List<ImageDataSave>>> uploadFile(ImageData imageData) {
        MultipartFile file = ImageUtil.createMultipartFileFromUrl(imageData);
        return ImageUtil.uploadImage(imageService, file);
    }

    @Override
    public ResponseEntity<ResponseMessage<List<ImageDataSave>>> uploadFile() {
        return null;
    }
}
