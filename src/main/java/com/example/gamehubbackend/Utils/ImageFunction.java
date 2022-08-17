package com.example.gamehubbackend.Utils;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
@Slf4j
public class ImageFunction {
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(originalImage)
                .size(targetWidth, targetHeight)
                .outputFormat("jpg")
                .outputQuality(1)
                .toOutputStream(outputStream);
        byte[] data = outputStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        return ImageIO.read(inputStream);
    }
    public static BufferedImage convertMultipartFile(MultipartFile file) {
        BufferedImage srcImage = null;
        try {
            InputStream in = (InputStream) file.getInputStream();
            srcImage = javax.imageio.ImageIO.read(in);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.error("image convert get error");
        }
        return srcImage;
    }

}
