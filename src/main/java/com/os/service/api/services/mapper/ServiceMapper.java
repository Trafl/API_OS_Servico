package com.os.service.api.services.mapper;

import com.os.service.api.groupServices.DTO.GroupAllDTOOutput;
import com.os.service.api.services.DTO.ServiceDTOInput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.domain.model.GroupServices;
import com.os.service.domain.model.Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class ServiceMapper {

    private final ModelMapper mapper;
    public ServiceDTOOutput toOneServiceDTOOutput(Service savedService) {
        return  mapper.map(savedService, ServiceDTOOutput.class);
    }

    public Service toModelService(ServiceDTOInput dtoInput){
        return mapper.map(dtoInput, Service.class);
    }

    public Page<ServiceDTOOutput> mapPageToDTO(Page<Service> groups) {
        return groups.map(group -> mapper.map(group, ServiceDTOOutput.class));
    }

}
