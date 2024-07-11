package com.os.service.api.groupServices.controller;

import com.os.service.api.groupServices.DTO.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.GroupDTOInput;
import com.os.service.api.groupServices.DTO.GroupDTONameOutput;
import com.os.service.api.groupServices.DTO.GroupOneDTOOutput;
import com.os.service.api.groupServices.mapper.GroupMapper;
import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.model.GroupServices;
import com.os.service.domain.service.GroupSServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/grupos_servicos")
@RequiredArgsConstructor
@Log4j2
public class GroupServiceController {

    final private GroupSServices services;

    final private GroupMapper mapper;

    final private PagedResourcesAssembler<GroupAllDTOOutput> pagedResourcesAssembler;

    final private ServiceMapper serviceMapper;

    private String timestamp = LocalDateTime.now().toString();

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<GroupAllDTOOutput>>> getAllGroups(@PageableDefault  Pageable pageable, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: GET, EndPoint: 'api/grupos_servicos'", timestamp, request.getRemoteAddr());

        Page<GroupServices> pageOfGroups = services.getAllGroups(pageable);

        Page<GroupAllDTOOutput> pageDto= mapper.mapPageToDTO(pageOfGroups);

        PagedModel<EntityModel<GroupAllDTOOutput>> paged = pagedResourcesAssembler.toModel(pageDto);

        return ResponseEntity.ok(paged);
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupOneDTOOutput> getOneGroupById(@PathVariable Long groupId, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: GET, EndPoint: 'api/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        var group = services.getGroupById(groupId);

        var groupDto = mapper.getOneDto(group);

        return ResponseEntity.ok(groupDto);
    }

    @PostMapping()
    public ResponseEntity<GroupOneDTOOutput> addGroupById(@Valid @RequestBody GroupDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: POST, EndPoint: 'api/grupos_servicos/'", timestamp, request.getRemoteAddr());

        var serviceModel = mapper.toModel(dtoInput);

        var group = services.addGroup(serviceModel);

        var groupDto = mapper.getOneDto(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }

    @PostMapping("/{groupId}/service")
    public ResponseEntity<GroupOneDTOOutput> addServiceToGroupById(@PathVariable Long groupId,
                                                                  @Valid @RequestBody ServiceDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: POST, EndPoint: 'api/grupos_servicos/{}/servicos'", timestamp, request.getRemoteAddr(),groupId);

        var serviceModel = serviceMapper.toModelService(dtoInput);

        var group = services.addServiceToGroup(groupId, serviceModel);

        var groupDto = mapper.getOneDto(group);

        return ResponseEntity.status(HttpStatus.CREATED).body(groupDto);
    }


    @PutMapping("/{groupId}")
    public ResponseEntity<GroupDTONameOutput> updateGroupById(@PathVariable Long groupId,
                                                                     @Valid @RequestBody GroupDTOInput dtoInput, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: PUT, EndPoint: 'api/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        var serviceModel = mapper.toModel(dtoInput);

        var group = services.updateGroup(groupId, serviceModel);

        var groupDto = mapper.toDTONameOutput(group);

        return ResponseEntity.ok(groupDto);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> disableGroupById(@PathVariable Long groupId, HttpServletRequest request){

        log.info("[{}] - [GroupServiceController] IP: {}, Request: DELETE, EndPoint: '/grupos_servicos/{}'", timestamp, request.getRemoteAddr(),groupId);

        services.disabledGroupById(groupId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
