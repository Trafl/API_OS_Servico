package com.os.service.api.serviceInOrder.controller;

import com.os.service.api.serviceInOrder.controller.openapi.ServiceInOrderControllerDocumentation;
import com.os.service.domain.services.aws.AwsServiceImpl;
import com.os.service.domain.services.serviceInOrder.ServiceInOrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@RestController
@Log4j2
@RequestMapping("/api/servico_ordem")
@RequiredArgsConstructor
public class ServiceInOrderController implements ServiceInOrderControllerDocumentation {

    private final ServiceInOrderService orderService;

    private String timestamp = LocalDateTime.now().toString();

    @PostMapping("/{serviceInOrderId}/foto_antes")
    public ResponseEntity<Void> addPhotoBeforeToOrder(@PathVariable Long serviceInOrderId, @RequestParam("file") MultipartFile image, HttpServletRequest request){
        log.info("[{}] - [ServiceInOrderController] IP: {}, Request: POST, EndPoint: '/api/servico_ordem/{}/foto_antes'", timestamp, request.getRemoteAddr(),serviceInOrderId );

        orderService.addPhotoBefore(serviceInOrderId, image);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{serviceInOrderId}/foto_depois")
    public ResponseEntity<Void> addPhotoAfterToOrder(@PathVariable Long serviceInOrderId, @RequestParam("file") MultipartFile image, HttpServletRequest request){
        log.info("[{}] - [ServiceInOrderController] IP: {}, Request: POST, EndPoint: '/api/servico_ordem/{}/foto_depois'", timestamp, request.getRemoteAddr(),serviceInOrderId );

        orderService.addPhotoAfter(serviceInOrderId, image);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
