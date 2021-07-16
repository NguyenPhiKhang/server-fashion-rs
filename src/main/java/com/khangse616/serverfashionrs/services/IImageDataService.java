package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface IImageDataService {
    ImageData findImageById(String id);
    List<ImageData> findListImageDataByIds(List<String> ids);
    List<ImageData> getAllImages();
    boolean checkExistsId(String id);
    List<ImageData> storesImageData(List<MultipartFile> multipartFiles);
    ImageData storeImageData(MultipartFile multipartFile);
//    ImageData storeImageData(MultipartFile multipartFile, String id);
    Optional<ImageData> getFile(String id);
    void saveImage(ImageData imageData);
    void removeImageById(String id);
}
