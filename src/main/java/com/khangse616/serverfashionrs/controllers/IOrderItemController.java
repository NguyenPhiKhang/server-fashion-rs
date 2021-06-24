package com.khangse616.serverfashionrs.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/default")
@CrossOrigin(value = {"http://localhost:3000"})
public interface IOrderItemController {
}
