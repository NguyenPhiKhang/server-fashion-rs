package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.Address;
import com.khangse616.serverfashionrs.models.dto.AddressDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class AddressDTOMapper implements RowMapper<AddressDTO, Address> {
    @Override
    public AddressDTO mapRow(Address address) {
        try{
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setName(address.getName());
            addressDTO.setNumberPhone(address.getNumberPhone());
            addressDTO.setSpecificAddress(address.getSpecificAddress());
            addressDTO.setCreatedAt(StringUtil.convertTimestampToString(address.getCreatedAt()));
            addressDTO.setUpdateAt(StringUtil.convertTimestampToString(address.getCreatedAt()));
            addressDTO.setProvince(address.getWard().getProvince());
            addressDTO.setDistrict(address.getWard().getDistrict());
            addressDTO.setWard(address.getWard());

            return addressDTO;
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public AddressDTO mapRow(Address address, IImageDataService repository) {
        return null;
    }
}
