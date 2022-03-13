package com.example.gamehubbackend.config.awsS3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import static com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead;

@Slf4j
@Service
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        //check it is image
        if (!filename.matches("^.*(png|jpg|jpeg)$"))
            throw new IllegalStateException("Only png or jpg file is accepted");
        //generate unique file name
        File fileObj = convertMultiPartFileToFile(file);
        Random random = new Random();
        String newFileName = String.format("%s%s",System.currentTimeMillis(),random.nextInt(100000)+filename);
        //uploadFile
        s3Client.putObject(new PutObjectRequest(bucketName,newFileName,fileObj).withCannedAcl(PublicRead));

        fileObj.delete();
        String urlForTheImage = "https://"+bucketName+".s3.amazonaws.com/"+newFileName;
        return urlForTheImage;
    }
    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
            throw new IllegalStateException("Error converting multipartFile to file");
        }
        return convertedFile;
    }
}
