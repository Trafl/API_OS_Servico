package com.os.service.api.serviceInOrder.controller;

import com.os.service.domain.services.aws.AwsServiceImpl;
import com.os.service.domain.services.serviceInOrder.ServiceInOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
@RequestMapping("/servico_ordem")
@RequiredArgsConstructor
public class ServiceInOrderController {

    private final ServiceInOrderService orderService;

    @PostMapping("/{serviceInOrderId}/foto_antes")
    public ResponseEntity<Void> addPhotoBeforeToOrder(@PathVariable Long serviceInOrderId, @RequestParam("file") MultipartFile image){

        orderService.addPhotoBefore(serviceInOrderId, image);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{serviceInOrderId}/foto_depois")
    public ResponseEntity<Void> addPhotoAfterToOrder(@PathVariable Long serviceInOrderId, @RequestParam("file") MultipartFile image){

        orderService.addPhotoAfter(serviceInOrderId, image);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
