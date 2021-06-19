package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageDataService {
    ImageData findImageById(String id);
    List<ImageData> findListImageDataByIds(List<String> ids);
    List<ImageData> getAllImages();
    boolean checkExistsId(String id);
    List<ImageData> storesImageData(List<MultipartFile> multipartFiles);
}
