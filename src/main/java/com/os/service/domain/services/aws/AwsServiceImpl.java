package com.os.service.domain.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.os.service.domain.exception.ErrorUploadPhotoForS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class AwsServiceImpl implements AwsService {

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    private final AmazonS3 s3;

    @Value("${IMAGE_WIDTH:300}")
    private int imageWidth;

    @Value("${IMAGE_HEIGHT:300}")
    private int imageHeight;

    private String timestamp = LocalDateTime.now().toString();
    @Override
    public String savePhotoInS3(MultipartFile file)  {
        String fileName;
        try{
            log.info("[{}] - [AwsServiceImpl] Executing savePhotoInS3 ", timestamp);

            BufferedImage originalImage = ImageIO.read(file.getInputStream());

            BufferedImage resizedImage = new BufferedImage(imageWidth, imageWidth,BufferedImage.TYPE_INT_RGB);

            Graphics2D g = resizedImage.createGraphics();
            g.drawImage(originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();

            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(imageBytes.length);
            metadata.setContentType("image/jpeg");

            s3.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(imageBytes), metadata));

            log.info("[{}] - [AwsServiceImpl] photo name: {}, add successful to S3",timestamp,fileName);
        }catch (IOException e){
            throw new ErrorUploadPhotoForS3Exception("Unable to upload the image to the S3 service");
        }

        return s3.getUrl(bucketName, fileName).toString();
    }
}
