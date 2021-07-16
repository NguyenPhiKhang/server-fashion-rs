package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.EnvUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.dto.FileRatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

public class FileRatingDTOMapper implements RowMapper<FileRatingDTO, ImageData> {
    @Override
    public FileRatingDTO mapRow(ImageData imageData) {
        try {
            FileRatingDTO fileRatingDTO = new FileRatingDTO();
            fileRatingDTO.setId(imageData.getId());
            fileRatingDTO.setContentType(imageData.getType().split("/")[0]);
//            fileRatingDTO.setLinkUrl(imageData.getData() != null ? EnvUtil.getInstance().getServerUrlPrefix() + "/api/v1/image/" + imageData.getId() : "http:" + imageData.getLink().replace("fill_size", "255x298"));
            fileRatingDTO.setLinkUrl(imageData.getData() != null ? "http://localhost:8080/api/v1/image/" + imageData.getId() : "http:" + imageData.getLink().replace("fill_size", "255x298"));
            return fileRatingDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public FileRatingDTO mapRow(ImageData imageData, IImageDataService repository) {
        return null;
    }
}
