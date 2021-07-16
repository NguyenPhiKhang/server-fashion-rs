package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.Utils.ImageUtil;
import com.khangse616.serverfashionrs.Utils.StringUtil;
import com.khangse616.serverfashionrs.models.ImageData;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.InputUserUpdateDTO;
import com.khangse616.serverfashionrs.repositories.UserRepository;
import com.khangse616.serverfashionrs.services.IImageDataService;
import com.khangse616.serverfashionrs.services.IUserService;
import lombok.SneakyThrows;
import net.andreinc.mockneat.MockNeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IImageDataService imageDataService;

    @Override
    public User getUserById(int userId) {
        return userRepository.findUserById(userId);
    }

    @Override
    public List<Integer> getListIdUser() {
        return userRepository.getAllIdUser();
    }

    @Override
    public List<User> getListUserFilter(String search, int active, int page, int pageSize) {
        int pageNew = page < 1 ? 0 : (page - 1) * pageSize;
        return userRepository.getListUserFilter(search, active, pageNew, pageSize);
    }

    @Override
    public int countListUserFilter(String search, int active) {
        return userRepository.countListUserFilter(search, active);
    }

    @Override
    public void autoCreateEmail() {
        List<User> users = userRepository.getListUserFilter("", -1, 0, 1000);

        MockNeat mock = MockNeat.threadLocal();

        users.forEach(user -> {
            String corpEmail;
            do {
                corpEmail = mock.emails().domain("gmail.com").val();
            } while (checkExistEmail(corpEmail));

            user.setEmail(corpEmail);
        });

        userRepository.saveAll(users);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User registerUser(String name, String email) {
        Random rd = new Random();

        int idUser;
        do {
            idUser = 100000000 + rd.nextInt(2000000000);
        } while (userRepository.existsById(idUser));

        User user = new User();
        user.setId(idUser);
        user.setName(name);
        user.setEmail(email);
        user.setActive(true);
        user.setTimeCreated(new Timestamp(System.currentTimeMillis()));
        user.setTimeUpdated(new Timestamp(System.currentTimeMillis()));

        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public User updateUser(InputUserUpdateDTO input_user) throws ParseException {
        User user = userRepository.findUserById(input_user.getId());
        user.setName(input_user.getName());
        user.setEmail(input_user.getEmail());
        user.setTimeUpdated(new Timestamp(System.currentTimeMillis()));
        user.setPhoneNumber(input_user.getPhoneNumber());
        user.setBirthday(StringUtil.convertStringToDate(input_user.getBirthday(), "yyyy-MM-dd"));

//        if (user.getImageAvatar() == null) {
//            if (input_user.getImage() != null) {
//                user.setImageAvatar(saveImage(input_user.getImage(), imageDataService));
//            }
//        }else{
//            if(input_user.getImage()!=null){
//                ImageData imageData = user.getImageAvatar();
//                MultipartFile multipartFile = new MockMultipartFile(imageData.getId(), imageData.getId(), input_user.getImage().getContentType(),  input_user.getImage().getInputStream());
//                imageData.setData(multipartFile.getBytes());
//                imageData.setLink(null);
//
//                imageDataService.saveImage(imageData);
//            }
//        }
        String idImage = "";

        if(input_user.getImage()!=null){
            if(user.getImageAvatar()!=null)
                idImage = user.getImageAvatar().getId();
            user.setImageAvatar(saveImage(input_user.getImage(), imageDataService));
        }

        userRepository.save(user);

        if(!idImage.equals(""))
            imageDataService.removeImageById(idImage);

        return user;
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
