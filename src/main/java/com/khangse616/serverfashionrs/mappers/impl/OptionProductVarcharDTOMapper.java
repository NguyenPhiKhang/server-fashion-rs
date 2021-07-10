package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.OptionProductVarcharDTO;
import com.khangse616.serverfashionrs.models.dto.RatingDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

import java.util.stream.Collectors;

public class OptionProductVarcharDTOMapper implements RowMapper<OptionProductVarcharDTO, OptionProductVarchar> {
    @Override
    public OptionProductVarcharDTO mapRow(OptionProductVarchar optionProductVarchar) {
        try {
            OptionProductVarcharDTO optionProductVarcharDTO = new OptionProductVarcharDTO();
            optionProductVarcharDTO.setId(optionProductVarchar.getId());
            optionProductVarcharDTO.setValue(optionProductVarchar.getValue());
            optionProductVarcharDTO.setAttributeId(optionProductVarchar.getAttribute().getId());

            return optionProductVarcharDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public OptionProductVarcharDTO mapRow(OptionProductVarchar optionProductVarchar, IImageDataService repository) {
        return null;
    }
}
