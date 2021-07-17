package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.models.Brand;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.dto.InputBrandDTO;
import com.khangse616.serverfashionrs.repositories.BrandRepository;
import com.khangse616.serverfashionrs.services.IBrandService;
import com.khangse616.serverfashionrs.services.IImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public List<Brand> getBrandFilter(String search) {
        return brandRepository.findBrandFilter(search);
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.findById(id).get();
    }

    @Override
    public void createNewCategory(InputBrandDTO inputBrand) {
        if (inputBrand.getId() == 0) {
            Brand brand = new Brand();
            Random rd = new Random();
            int idBrand;
            do {
                idBrand = 100 + rd.nextInt(6000001);
            } while (brandRepository.existsById(idBrand));

            brand.setId(idBrand);
            brand.setName(inputBrand.getName());

            ImageData imageData = saveImage(inputBrand.getIcon(), imageDataService);

            brand.setIcon("http://localhost:8080/api/v1/image/" + imageData.getId());

            brandRepository.save(brand);
        } else {
            Brand brand = brandRepository.findById(inputBrand.getId()).orElse(null);
            if (brand != null) {
                brand.setName(inputBrand.getName());
                if (inputBrand.getIcon() != null) {
                    ImageData imageData = saveImage(inputBrand.getIcon(), imageDataService);

                    brand.setIcon("http://localhost:8080/api/v1/image/" + imageData.getId());
                }

                brandRepository.save(brand);
            }
        }
    }

    private ImageData saveImage(MultipartFile image, IImageDataService imageDataService) {
        String fileName = ImageUtil.fileName(imageDataService, image);
        try {
            MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, image.getContentType(), image.getInputStream());

            return imageDataService.storeImageData(multipartFile);
        } catch (IOException e) {
            return null;
        }
    }
}

