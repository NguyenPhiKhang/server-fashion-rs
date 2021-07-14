package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IUserController;
import com.khangse616.serverfashionrs.mappers.impl.UserDTOMapper;
import com.khangse616.serverfashionrs.models.dto.UserDTO;
import com.khangse616.serverfashionrs.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/")
public class UserController implements IUserController {
    @Autowired
    private IUserService userService;

    @Override
    public List<UserDTO> getListUserFilter(String search, int active, int page, int pageSize) {
        return userService.getListUserFilter(search, active, page, pageSize).stream().map(v-> new UserDTOMapper().mapRow(v)).collect(Collectors.toList());
    }

    @Override
    public int countUsersFilter(String search, int active) {
        return userService.countListUserFilter(search, active);
    }
}
