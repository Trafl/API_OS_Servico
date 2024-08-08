package com.os.service.domain.services.aws;

import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

    String savePhotoInS3(MultipartFile file);
}
