package com.os.service.domain.services.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.os.service.domain.exception.ErrorUploadPhotoForS3Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class AwsServiceImpl implements AwsService {

    @Value("${AWS_BUCKET_NAME}")
    private String bucketName;

    private final AmazonS3 s3;

    private String timestamp = LocalDateTime.now().toString();
    @Override
    public String savePhotoInS3(MultipartFile file)  {
        String fileName;
        try{
            log.info("[{}] - [AwsServiceImpl] Executing savePhotoInS3 ", timestamp);

            fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            s3.putObject(new PutObjectRequest(bucketName, fileName, file.getInputStream(), null));

            log.info("[{}] - [AwsServiceImpl] photo name: {}, add successful to S3",timestamp,fileName);
        }catch (IOException e){
            throw new ErrorUploadPhotoForS3Exception("Unable to upload the image to the S3 service");
        }

        return s3.getUrl(bucketName, fileName).toString();
    }
}
