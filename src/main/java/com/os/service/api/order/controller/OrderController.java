package com.os.service.api.order.controller;


import com.os.service.api.order.DTO.generatorstatus.GeneratorStatusDTO;
import com.os.service.api.order.DTO.generatortest.GeneratorTestDTO;
import com.os.service.api.order.DTO.input.GeneralObservationsDTOInput;
import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.order.DTO.output.OrderAllDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.api.order.DTO.output.OrderOnePdfDTOOutput;
import com.os.service.api.order.mapper.OrderMapper;

import com.os.service.api.order.controller.openapi.OrderControllerDocumentation;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordem")
@CrossOrigin(origins = "*", exposedHeaders = {"x-total-count", "x-total-pages", "x-page-number", "x-page-size"})
public class OrderController implements OrderControllerDocumentation {

    private final OrderService orderService;

    private final OrderMapper mapper;

    final private PagedResourcesAssembler<OrderAllDTOOutput> pagedResourcesAssembler;

    final private ServiceInOrderMapper serviceInOrderMapper;

    private String timestamp = LocalDateTime.now().toString();

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<OrderAllDTOOutput>>> getAllOrders(@PageableDefault(size = 5) Pageable pageable,
                                                                           HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem'", timestamp, request.getRemoteAddr());

        Page<Order> pageOfOrders = orderService.getAllOrders(pageable);

        Page<OrderAllDTOOutput> pageDto = mapper.mapPageToDTO(pageOfOrders);

        PagedModel<EntityModel<OrderAllDTOOutput>> paged = pagedResourcesAssembler.toModel(pageDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", Long.toString(pageDto.getTotalElements()));
        headers.add("x-total-pages", Integer.toString(pageDto.getTotalPages()));
        headers.add("x-page-number", Integer.toString(pageDto.getNumber()));
        headers.add("x-page-size", Integer.toString(pageDto.getSize()));

        return ResponseEntity.ok().headers(headers).body(paged);
    }

    @GetMapping("/filtro")
    public ResponseEntity<PagedModel<EntityModel<OrderAllDTOOutput>>> getOrderByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDay,
                                                                  @PageableDefault(size = 5) Pageable pageable, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem/filtro?startDay={}&endDay={}'", timestamp, request.getRemoteAddr(), startDay,endDay);

        LocalDateTime startDayWithTIme = startDay.atStartOfDay();
        LocalDateTime endDayWithTIme = endDay.atTime(LocalTime.MAX);

        var pageOrder = orderService.GetOrderByDate(pageable, startDayWithTIme, endDayWithTIme);

        var PageOrderDTO = mapper.mapPageToDTO(pageOrder);

        PagedModel<EntityModel<OrderAllDTOOutput>> paged = pagedResourcesAssembler.toModel(PageOrderDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", Long.toString(PageOrderDTO.getTotalElements()));
        headers.add("x-total-pages", Integer.toString(PageOrderDTO.getTotalPages()));
        headers.add("x-page-number", Integer.toString(PageOrderDTO.getNumber()));
        headers.add("x-page-size", Integer.toString(PageOrderDTO.getSize()));

        return ResponseEntity.ok().headers(headers).body(paged);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderOneDTOOutput> getOneOrder(@PathVariable Long orderId, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem/{}'", timestamp,orderId, request.getRemoteAddr());
        var savedOrder = orderService.getOneOrderById(orderId);
        var orderDTO = mapper.toDTOOne(savedOrder);

        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/abertas")
    public ResponseEntity<Integer> getOpenOrders(HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/abertas'", timestamp, request.getRemoteAddr());
        var countOrders = orderService.countOrderByStatus();

        return ResponseEntity.ok(countOrders);
    }

    @PostMapping
    public ResponseEntity<OrderOneDTOOutput> addOrder(@RequestBody @Valid OrderDTOInput dtoInput, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem'", timestamp, request.getRemoteAddr());

        var orderModel = mapper.toModel(dtoInput);

        var savedOrder = orderService.addOrder(orderModel);

        var orderDto = mapper.toDTOOne(savedOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PutMapping("/{orderId}/iniciar")
    public ResponseEntity<Void> startOrder(@PathVariable Long orderId, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: PUT, EndPoint: 'api/ordem/{}/iniciar'", timestamp, request.getRemoteAddr(), orderId);
        orderService.starOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/finalizar")
    public ResponseEntity<Void> finishOrder(@PathVariable Long orderId, @RequestBody GeneralObservationsDTOInput dtoInput, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: PUT, EndPoint: 'api/ordem/{}/finalizar'", timestamp, request.getRemoteAddr(), orderId);
        orderService.closeOrder(orderId, dtoInput.getGeneralObservations());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}/cancelar")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: PUT, EndPoint: 'api/ordem/{}/cancelar'", timestamp, request.getRemoteAddr(), orderId);
        orderService.cancelOrder(orderId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{orderId}/servico")
    public ResponseEntity<OrderOneDTOOutput> addServiceToOrder(@PathVariable Long orderId,
                                                               @Valid @RequestBody ServiceInOrderDTOInput dtoInput, HttpServletRequest request){
        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem/{}'", timestamp, request.getRemoteAddr(), orderId);
        var serviceModel = serviceInOrderMapper.toModel(dtoInput);

        var orderWithService = orderService.addServiceToOrder(orderId, serviceModel);

        var dtOutput = mapper.toDTOOne(orderWithService);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtOutput);
    }

    @PostMapping("/{orderId}/gerador_teste")
    public ResponseEntity<Void> addGeneratorTestToOrder(@PathVariable Long orderId, @RequestBody GeneratorTestDTO dtoInput, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem/{}/gerador_teste'", timestamp, request.getRemoteAddr(), orderId);

        var generatorTest = mapper.toTesteModel(dtoInput);

        orderService.addGeneratorTestToOrder(orderId, generatorTest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/{orderId}/gerador_status")
    public ResponseEntity<Void> addGeneratorStatusToOrder(@PathVariable Long orderId, @RequestBody GeneratorStatusDTO dtoInput, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: POST, EndPoint: 'api/ordem/{}/gerador_status'", timestamp, request.getRemoteAddr(), orderId);

        var generatorStatus = mapper.toStatusModel(dtoInput);

        orderService.addGeneratorStatusToOrder(orderId, generatorStatus);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{orderId}/pdf")
    public ResponseEntity<OrderOnePdfDTOOutput> getOneOrderForPDF(@PathVariable Long orderId, HttpServletRequest request){

        log.info("[{}] - [OrderController] IP: {}, Request: GET, EndPoint: 'api/ordem/{}/pdf'", timestamp,orderId, request.getRemoteAddr());
        var savedOrder = orderService.getOneOrderById(orderId);
        var orderDTO = mapper.toDTOPdf(savedOrder);

        return ResponseEntity.ok(orderDTO);
    }

}
