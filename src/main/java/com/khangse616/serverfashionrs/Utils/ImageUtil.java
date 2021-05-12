package com.khangse616.serverfashionrs.Utils;

import com.khangse616.serverfashionrs.messages.ResponseMessage;
import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImageUtil {
    public static ResponseEntity<ResponseMessage<ImageData>> uploadImage(ImageService imageService, MultipartFile file) {
        if (file != null) {
            try {
                Image image = imageService.store(file);

                String message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage<>(message, image));
            } catch (Exception e) {
                String message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage<>(message));
            }
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage<>(""));
        }
    }

    public static MultipartFile createMultipartFileFromUrl(ImageData imageData) {
        String url = imageData.getLink();
        if (url.equals("") || url.isEmpty())
            return null;
        try {
            URL imageUrl = url.contains("https://") ? new URL(url) : new URL("https:" + url.replace("fill_size", "700x817"));

            System.out.println(imageUrl);
            /********
             * Step 1
             * Create Buffered Image by Reading from Url using ImageIO library
             ********/
            BufferedImage image = ImageIO.read(imageUrl);

            /********
             * Step 2
             * Create ByteArrayOutputStream object to handle Image data
             ********/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            /********
             * Step 3
             * Write as Image with Jpeg extension to byteArrayOutputStream created in previous step
             ********/
            String typeImage = imageData.getType().split("/")[1];
            ImageIO.write(image, typeImage, byteArrayOutputStream);

            /********
             * Step 4
             * Flush image created to byteArrayOutoputStream
             ********/
            byteArrayOutputStream.flush();

            /********
             * Step 5
             * Now Create MultipartFile using MockMultipartFile by providing
             * @param fileName name of the file
             * @param imageType like "image/jpg"
             * @param ByteArray from ByteArrayOutputStream
             ********/
            MultipartFile multipartFile = new MockMultipartFile(imageData.getId(), imageData.getId(), "image/" + typeImage, byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close(); // Close once it is done saving
            return multipartFile;
        } catch (Exception exception) {
            return null;
        }
    }

    public static List<MultipartFile> createMultipartFileFromUrls(List<ImageData> imageDataList) throws IOException {
        List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
        for (ImageData imageData: imageDataList) {
            MultipartFile file = createMultipartFileFromUrl(imageData);
            if (file != null)
                multipartFiles.add(file);
        }
        return multipartFiles;
    }
}
