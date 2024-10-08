package com.os.service.api.services.controller;

import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.api.services.controller.openapi.ServiceControllerDocumentation;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.services.service.ServiceServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Log4j2
@RestController
@RequestMapping("/api/servicos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", exposedHeaders = {"x-total-count", "x-total-pages", "x-page-number", "x-page-size"})
public class ServiceController implements ServiceControllerDocumentation {

    private final ServiceServices service;

    private final ServiceMapper mapper;
    private String timestamp = LocalDateTime.now().toString();

    final private PagedResourcesAssembler<ServiceDTOOutput> pagedResourcesAssembler;

    @GetMapping
 //   @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<PagedModel<EntityModel<ServiceDTOOutput>>> getAllServices(@PageableDefault(size = 5) Pageable pageable, HttpServletRequest request){

        log.info("[{}] - [ServiceController] IP: {}, Request: GET, EndPoint: '/api/servicos'", timestamp, request.getRemoteAddr());

        var pageOfService = service.getAllServices(pageable);

        var listDto = mapper.mapPageToDTO(pageOfService);

        PagedModel<EntityModel<ServiceDTOOutput>> pageDto = pagedResourcesAssembler.toModel(listDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", Long.toString(pageOfService.getTotalElements()));
        headers.add("x-total-pages", Integer.toString(pageOfService.getTotalPages()));
        headers.add("x-page-number", Integer.toString(pageOfService.getNumber()));
        headers.add("x-page-size", Integer.toString(pageOfService.getSize()));

        return ResponseEntity.ok().headers(headers).body(pageDto);
    }

    @GetMapping("/{serviceId}")
 //   @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<ServiceDTOOutput> getOneServiceById(@PathVariable Long serviceId, HttpServletRequest request){

        log.info("[{}] - [ServiceController] IP: {}, Request: GET, EndPoint: '/api/servicos/{}'", timestamp, request.getRemoteAddr(), serviceId);

        var savedService = service.getServiceById(serviceId);

        var Dto = mapper.toOneServiceDTOOutput(savedService);

        return ResponseEntity.ok(Dto);
    }

    @PutMapping("/{serviceId}")
//    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<ServiceDTOOutput> updateOneServiceById(@PathVariable Long serviceId,
                                                                 @RequestBody @Valid ServiceDTOInput newService, HttpServletRequest request){

        log.info("[{}] - [ServiceController] IP: {}, Request: PUT, EndPoint: '/api/servicos/{}'", timestamp, request.getRemoteAddr(), serviceId);

        var serviceModel = mapper.toModelService(newService);

        var updatedService = service.updateService(serviceId,serviceModel);

        var Dto = mapper.toOneServiceDTOOutput(updatedService);

        return ResponseEntity.ok(Dto);
    }

    @DeleteMapping("/{serviceId}")
 //   @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<Void> deleteServiceById(@PathVariable Long serviceId, HttpServletRequest request){

        log.info("[{}] - [ServiceController] IP: {}, Request: DELETE, EndPoint: '/api/servicos/{}'", timestamp, request.getRemoteAddr(), serviceId);
        service.deleteServiceById(serviceId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
