package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.ImageDataSave;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface IImageDataSaveService {
    ImageDataSave store(MultipartFile file);

    List<ImageDataSave> stores(List<MultipartFile> multipartFiles);

    Optional<ImageDataSave> getFile(String id);

    Stream<ImageDataSave> getAllFiles();

    List<ImageDataSave> getAllImages();

    boolean checkExistsId(String id);
}
