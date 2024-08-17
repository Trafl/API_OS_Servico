package com.os.service.api.serviceInOrder.controller;

import com.os.service.api.serviceInOrder.DTO.output.ServiceInOrderDTOOutput;
import com.os.service.api.serviceInOrder.controller.openapi.ServiceInOrderControllerDocumentation;
import com.os.service.api.serviceInOrder.mapper.ServiceInOrderMapper;
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
@CrossOrigin(origins = "*", exposedHeaders = {"x-total-count", "x-total-pages", "x-page-number", "x-page-size"})
public class ServiceInOrderController implements ServiceInOrderControllerDocumentation {

    private final ServiceInOrderService orderService;

    private final ServiceInOrderMapper serviceInOrderMapper;
    
    private String timestamp = LocalDateTime.now().toString();

    @GetMapping("/{serviceInOrderId}")
    public ResponseEntity<ServiceInOrderDTOOutput> getServiceInOrderById(@PathVariable Long serviceInOrderId, HttpServletRequest request){

        log.info("[{}] - [ServiceInOrderController] IP: {}, Request: GET, EndPoint: '/api/servico_ordem/{}'", timestamp, request.getRemoteAddr(),serviceInOrderId );

        var intPut =orderService.getServiceById(serviceInOrderId);

        var outPut = serviceInOrderMapper.toDTO(intPut);

        return ResponseEntity.status(HttpStatus.OK).body(outPut);
    }

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

    @DeleteMapping("/{serviceInOrderId}")
    public ResponseEntity<Void> deleteServiceInOrder(@PathVariable Long serviceInOrderId, HttpServletRequest request){
        log.info("[{}] - [ServiceInOrderController] IP: {}, Request: DELETE, EndPoint: '/api/servico_ordem/{}", timestamp, request.getRemoteAddr(),serviceInOrderId );

        orderService.deleteServiceInOrderById(serviceInOrderId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
