package com.khangse616.serverfashionrs.mappers.impl;

import com.khangse616.serverfashionrs.Utils.EnvUtil;
import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.mappers.RowMapper;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.SearchProductDTO;
import com.khangse616.serverfashionrs.models.dto.UserDTO;
import com.khangse616.serverfashionrs.services.IImageDataService;

public class UserDTOMapper implements RowMapper<UserDTO, User> {

    @Override
    public UserDTO mapRow(User user) {
//        2035251707
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setActive(user.isActive());
            userDTO.setName(user.getName());
            userDTO.setEmail(user.getEmail());
            userDTO.setBirthday(user.getBirthday() != null ? StringUtil.convertDateToString(user.getBirthday()) : null);
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setSex(user.getSex());
            userDTO.setTimeCreated(StringUtil.convertTimestampToString(user.getTimeCreated()));
            userDTO.setTimeUpdated(StringUtil.convertTimestampToString(user.getTimeUpdated()));

//            userDTO.setImage_url(user.getImageAvatar() == null ? "https://media3.scdn.vn/images/apps/icon_user_default.png" : user.getImageAvatar().getData() != null ? EnvUtil.getInstance().getServerUrlPrefix() + "/api/v1/image/" + user.getImageAvatar().getId() : user.getImageAvatar().getLink());
            userDTO.setImage_url(user.getImageAvatar() == null ? "https://media3.scdn.vn/images/apps/icon_user_default.png" : user.getImageAvatar().getData() != null ? "http://localhost:8080/api/v1/image/" + user.getImageAvatar().getId() : user.getImageAvatar().getLink());

            user.getAddresses().forEach(a->{
                if(a.isDefaultIs()){
                    userDTO.setAddress(new AddressDTOMapper().mapRow(a));
                }
            });

            return userDTO;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public UserDTO mapRow(User user, IImageDataService repository) {
        return null;
    }
}
