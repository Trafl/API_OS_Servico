package com.os.service.domain.services.aws;

import com.os.service.api.order.DTO.input.OrderOnePdfDTOInput;
import org.springframework.web.multipart.MultipartFile;

public interface AwsService {

    String savePhotoInS3(MultipartFile file);

    void deletePhotoS3(String path);

    String saveJsonPDFS3(OrderOnePdfDTOInput orderOnePdfDTOInput);
}
