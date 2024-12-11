package com.os.service.domain.services.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.os.service.api.order.DTO.input.OrderOnePdfDTOInput;
import com.os.service.domain.exception.ErrorUploadPhotoForS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class AwsServiceImpl implements AwsService {

    private final ObjectMapper objectMapper;

    private final AmazonS3 s3;

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

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

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Invalid file type. Only images are allowed.");
            }

            String formatName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1).toLowerCase();
            if (formatName.equals("jpeg")) {
                formatName = "jpg";
            }

            BufferedImage resizedImage = Thumbnails.of(file.getInputStream())
                    .size(imageWidth, imageHeight)
                    .outputQuality(0.85)
                    .asBufferedImage();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, formatName, baos);
            byte[] imageBytes = baos.toByteArray();

            fileName = System.currentTimeMillis() + "_";

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(imageBytes.length);
            metadata.setContentType(contentType);

            s3.putObject(new PutObjectRequest(bucketName, fileName, new ByteArrayInputStream(imageBytes), metadata));

            log.info("[{}] - [AwsServiceImpl] photo name: {}, add successful to S3",timestamp,fileName);

        }catch (AmazonServiceException e) {
            log.error("Amazon service error while uploading to S3: {}", e.getMessage());
            throw new ErrorUploadPhotoForS3Exception("Amazon service error occurred.");
        } catch (SdkClientException e) {
            log.error("AWS SDK error: {}", e.getMessage());
            throw new ErrorUploadPhotoForS3Exception("AWS SDK client error occurred.");
        } catch (IOException e) {
            log.error("IO error: {}", e.getMessage());
            throw new ErrorUploadPhotoForS3Exception("Error processing the image.");
        }

        return s3.getUrl(bucketName, fileName).toString();
    }

    public void deletePhotoS3(String path) {
        log.info("[{}] - [AwsServiceImpl] executing deletePhotoS3 ", timestamp);

        String filename = path.substring(path.lastIndexOf("/") + 1);

        s3.deleteObject(new DeleteObjectRequest(bucketName, filename));
        log.info("[{}] - [AwsServiceImpl] photo path: {}, delete successful ", timestamp, filename);
    }

    public String saveJsonPDFS3(OrderOnePdfDTOInput orderOnePdfDTOInput){
        log.info("[{}] - [AwsServiceImpl] Executing saveJsonPDFS3 ", timestamp);

        String fileName = String.format("Ordem-%d.json", orderOnePdfDTOInput.getId());

        try {
            String pdfJson = objectMapper.writeValueAsString(orderOnePdfDTOInput);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("application/json");
            metadata.setContentLength(pdfJson.getBytes(StandardCharsets.UTF_8).length);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfJson.getBytes(StandardCharsets.UTF_8));

            s3.putObject(bucketName, fileName, inputStream, metadata);
            log.info("[{}] - [AwsServiceImpl] Json send to S3 ", timestamp);

            return s3.getUrl(bucketName, fileName).toString();

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error processing object to JSON", e);

        } catch (AmazonServiceException e) {
        log.error("Amazon service error while uploading to S3: {}", e.getMessage());
            throw new ErrorUploadPhotoForS3Exception("Amazon service error occurred.");

        } catch (SdkClientException e) {
            log.error("AWS SDK error: {}", e.getMessage());
            throw new ErrorUploadPhotoForS3Exception("AWS SDK client error occurred.");
        }
    }
}
