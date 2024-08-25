package com.os.service.api.groupServices.controller;

import com.os.service.api.groupServices.DTO.input.GroupDTOInput;
import com.os.service.api.groupServices.DTO.output.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.output.GroupDTONameOutput;
import com.os.service.api.groupServices.DTO.output.GroupOneDTOOutput;
import com.os.service.api.groupServices.controller.openapi.GroupServiceControllerDocumentation;
import com.os.service.api.groupServices.mapper.GroupMapper;
import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.services.groupservice.GroupSServices;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/grupos_servicos")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin(origins = "*", exposedHeaders = {"x-total-count", "x-total-pages", "x-page-number", "x-page-size"})
public class GroupServiceController implements GroupServiceControllerDocumentation {

    final private GroupSServices services;

    final private GroupMapper mapper;

    final private PagedResourcesAssembler<GroupAllDTOOutput> pagedResourcesAssembler;

    final private ServiceMapper serviceMapper;

    final private PagedResourcesAssembler<ServiceDTOOutput> servicePagedResourcesAssembler;

    private String timestamp = LocalDateTime.now().toString();

    @GetMapping
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<PagedModel<EntityModel<GroupAllDTOOutput>>> getAllGroups(@PageableDefault(size = 5)  Pageable pageable, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: GET, EndPoint: 'api/grupos_servicos'", timestamp, request.getRemoteAddr());

        Page<GroupServices> pageOfGroups = services.getAllGroups(pageable);

        Page<GroupAllDTOOutput> pageDto= mapper.mapPageToDTO(pageOfGroups);

        PagedModel<EntityModel<GroupAllDTOOutput>> paged = pagedResourcesAssembler.toModel(pageDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", Long.toString(pageDto.getTotalElements()));
        headers.add("x-total-pages", Integer.toString(pageDto.getTotalPages()));
        headers.add("x-page-number", Integer.toString(pageDto.getNumber()));
        headers.add("x-page-size", Integer.toString(pageDto.getSize()));

        return ResponseEntity.ok().headers(headers).body(paged);
    }

    @GetMapping("/{groupId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<PagedModel<EntityModel<ServiceDTOOutput>>> getServicesOfGroupById(@PathVariable Long groupId, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: GET, EndPoint: 'api/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        var servicesPage = services.getServicesByGroupId(pageable, groupId);

        var servicePageDto = serviceMapper.mapPageToDTO(servicesPage);

        PagedModel<EntityModel<ServiceDTOOutput>> paged = servicePagedResourcesAssembler.toModel(servicePageDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-total-count", Long.toString(servicePageDto.getTotalElements()));
        headers.add("x-total-pages", Integer.toString(servicePageDto.getTotalPages()));
        headers.add("x-page-number", Integer.toString(servicePageDto.getNumber()));
        headers.add("x-page-size", Integer.toString(servicePageDto.getSize()));

        return ResponseEntity.ok().headers(headers).body(paged);
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<GroupOneDTOOutput> addGroup(@Valid @RequestBody GroupDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: POST, EndPoint: 'api/grupos_servicos/'", timestamp, request.getRemoteAddr());

        var serviceModel = mapper.toModel(dtoInput);

        var group = services.addGroup(serviceModel);

        var groupDto = mapper.getOneDto(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }

    @PostMapping("/{groupId}/service")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<GroupOneDTOOutput> addServiceToGroupById(@PathVariable Long groupId,
                                                                  @Valid @RequestBody ServiceDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: POST, EndPoint: 'api/grupos_servicos/{}/servicos'", timestamp, request.getRemoteAddr(),groupId);

        var serviceModel = serviceMapper.toModelService(dtoInput);

        var group = services.addServiceToGroup(groupId, serviceModel);

        var groupDto = mapper.getOneDto(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }


    @PutMapping("/{groupId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<GroupDTONameOutput> updateGroupById(@PathVariable Long groupId,
                                                                     @Valid @RequestBody GroupDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: PUT, EndPoint: 'api/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        var serviceModel = mapper.toModel(dtoInput);

        var group = services.updateGroup(groupId, serviceModel);

        var groupDto = mapper.toDTONameOutput(group);

        return ResponseEntity.ok(groupDto);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<Void> disableGroupById(@PathVariable Long groupId, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: DELETE, EndPoint: '/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        services.disabledGroupById(groupId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
