package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.repositories.ImageDataRepository;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageDataService implements IImageDataService {
    @Autowired
    private ImageDataRepository imageDataRepository;

    @Override
    public ImageData findImageById(String id) {
        return imageDataRepository.findById(id);
    }

    @Override
    public List<ImageData> findListImageDataByIds(List<String> ids) {
        return imageDataRepository.findByIdIn(ids);
    }
}
