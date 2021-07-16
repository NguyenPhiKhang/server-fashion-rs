package com.khangse616.serverfashionrs.controllers;

import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.InputUserUpdateDTO;
import com.khangse616.serverfashionrs.models.dto.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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

    @PostMapping("/users/auto-created-email")
    String createEmailAuto();

    @GetMapping("/user/{userId}/get-detail")
    UserDTO getDetailUserById(@PathVariable("userId") int userId);

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    UserDTO updateUser(@ModelAttribute("input_user")InputUserUpdateDTO input_user) throws ParseException;


}