package com.os.service.domain.services.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsService {

    public String savePhotoInS3(MultipartFile file);
}
