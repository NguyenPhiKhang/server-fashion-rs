package com.khangse616.serverfashionrs.controllers.impl;

import com.khangse616.serverfashionrs.controllers.IImageController;
import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.repositories.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ImageController implements IImageController {
}
