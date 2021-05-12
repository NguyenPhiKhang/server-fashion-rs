package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.ImageDataSave;
import com.khangse616.serverfashionrs.repositories.ImageDataSaveRepository;
import com.khangse616.serverfashionrs.services.IImageDataSaveService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ImageDataSaveService implements IImageDataSaveService {
    @Autowired
    private ImageDataSaveRepository imageDataSaveRepository;

    @SneakyThrows
    @Override
    public ImageDataSave store(MultipartFile file) {
        String fileName = StringUtils.cleanPath((Objects.requireNonNull(file.getOriginalFilename())));
        ImageDataSave FileDB = new ImageDataSave(fileName, fileName, file.getContentType(), file.getBytes());

        return imageDataSaveRepository.save(FileDB);
    }

    @SneakyThrows
    @Override
    public List<ImageDataSave> stores(List<MultipartFile> multipartFiles) {
        List<ImageDataSave> images = new ArrayList<ImageDataSave>();
        for (MultipartFile file : multipartFiles) {
            String fileName = StringUtils.cleanPath((Objects.requireNonNull(file.getOriginalFilename())));
            ImageDataSave FileDB = new ImageDataSave(fileName, fileName, file.getContentType(), file.getBytes());

            images.add(FileDB);
        }
        return imageDataSaveRepository.saveAll(images);
    }

    @Override
    public Optional<ImageDataSave> getFile(String id) {
        return imageDataSaveRepository.findById(id);
    }

    @Override
    public Stream<ImageDataSave> getAllFiles() {
        return imageDataSaveRepository.findAll().stream();
    }

    @Override
    public List<ImageDataSave> getAllImages() {
        return imageDataSaveRepository.findAll();
    }

    @Override
    public boolean checkExistsId(String id) {
        return imageDataSaveRepository.existsById(id);
    }
}
