package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.dto.UserDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface IUserController {
    @GetMapping("/users/get-users-filter")
    List<UserDTO> getListUserFilter(@RequestParam(value = "search", defaultValue = "") String search,
                                    @RequestParam(value = "status", defaultValue = "-1") int active,
                                    @RequestParam(value = "p", defaultValue = "1") int page,
                                    @RequestParam(value = "p_size", defaultValue = "5") int pageSize);

    @GetMapping("/users/count-users-filter")
    int countUsersFilter(@RequestParam(value = "search", defaultValue = "") String search,
                         @RequestParam(value = "status", defaultValue = "-1") int active);
}