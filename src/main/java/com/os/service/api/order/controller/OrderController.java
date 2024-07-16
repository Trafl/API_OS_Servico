package com.os.service.api.order.controller;


import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.order.DTO.output.OrderAllDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.api.order.DTO.output.OrderOnePdfDTOOutput;
import com.os.service.api.order.mapper.OrderMapper;

import com.os.service.api.serviceInOrder.DTO.input.ServiceInOrderDTOInput;
import com.os.service.api.serviceInOrder.mapper.ServiceInOrderMapper;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.services.order.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordem")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper mapper;

    final private PagedResourcesAssembler<OrderAllDTOOutput> pagedResourcesAssembler;

    final private ServiceInOrderMapper serviceInOrderMapper;

    private String timestamp = LocalDateTime.now().toString();

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<OrderAllDTOOutput>>> getAllOrders(@PageableDefault Pageable pageable,
                                                                           HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem'", timestamp, request.getRemoteAddr());

        Page<Order> pageOfOrders = orderService.getAllOrders(pageable);

        Page<OrderAllDTOOutput> pageDto = mapper.mapPageToDTO(pageOfOrders);

        PagedModel<EntityModel<OrderAllDTOOutput>> paged = pagedResourcesAssembler.toModel(pageDto);

        return ResponseEntity.ok(paged);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderOneDTOOutput> getOneOrder(@PathVariable Long orderId, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem/{}'", timestamp,orderId, request.getRemoteAddr());
        var savedOrder = orderService.getOneOrderById(orderId);
        var orderDTO = mapper.toDTOOne(savedOrder);

        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderOneDTOOutput> addOrder(@RequestBody @Valid OrderDTOInput dtoInput, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem'", timestamp, request.getRemoteAddr());

        var orderModel = mapper.toModel(dtoInput);

        var savedOrder = orderService.addOrder(orderModel);

        var orderDto = mapper.toDTOOne(savedOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<OrderOneDTOOutput> addServiceToOrder(@PathVariable Long orderId,
                                        @Valid @RequestBody ServiceInOrderDTOInput dtoInput, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem/{}'", timestamp, request.getRemoteAddr(), orderId);
        var serviceModel = serviceInOrderMapper.toModel(dtoInput);

        var orderWithService = orderService.addServiceToOrder(orderId, serviceModel);

        var dtOutput = mapper.toDTOOne(orderWithService);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtOutput);
    }

    // Cria metodo para usar a AWS

    @PutMapping("/{orderId}/iniciar")
    public void startOrder(@PathVariable Long orderId, HttpServletRequest request){
        orderService.starOrder(orderId);
    }

    @PutMapping("/{orderId}/finalizar")
    public void finishOrder(@PathVariable Long orderId, HttpServletRequest request){
        orderService.closeOrder(orderId);
    }

    @PutMapping("/{orderId}/cancelar")
    public void cancelOrder(@PathVariable Long orderId, HttpServletRequest request){
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/{orderId}/pdf")
    public ResponseEntity<OrderOnePdfDTOOutput> getOneOrderForPDF(@PathVariable Long orderId, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem/{}/pdf'", timestamp,orderId, request.getRemoteAddr());
        var savedOrder = orderService.getOneOrderById(orderId);
        var orderDTO = mapper.toDTOPdf(savedOrder);

        return ResponseEntity.ok(orderDTO);
    }

}
