package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.ImageDataSave;
import com.khangse616.serverfashionrs.repositories.ImageDataRepository;
import com.khangse616.serverfashionrs.services.IImageDataService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageDataService implements IImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    @Override
    public ImageData findImageById(String id) {
        return imageDataRepository.findImageDataById(id);
    }

    @Override
    public List<ImageData> findListImageDataByIds(List<String> ids) {
        return imageDataRepository.findByIdIn(ids);
    }

    @Override
    public List<ImageData> getAllImages() {
        return imageDataRepository.findAll();
    }

    @Override
    public boolean checkExistsId(String id) {
        return imageDataRepository.existsById(id);
    }

    @Override
    @SneakyThrows
    public List<ImageData> storesImageData(List<MultipartFile> multipartFiles) {
        List<ImageData> images = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath((Objects.requireNonNull(file.getOriginalFilename())));
            ImageData FileDB = new ImageData(fileName, fileName, file.getContentType(), file.getBytes());

            System.out.println(fileName);

            images.add(FileDB);
        }
        return imageDataRepository.saveAll(images);
    }
}
