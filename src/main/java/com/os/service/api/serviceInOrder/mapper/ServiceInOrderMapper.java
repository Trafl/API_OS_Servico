package com.os.service.api.serviceInOrder.mapper;

import com.os.service.api.serviceInOrder.DTO.input.ServiceInOrderDTOInput;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceInOrderMapper {

    private final ModelMapper mapper;
    public ServiceInOrder toModel(ServiceInOrderDTOInput dtoInput){
        return mapper.map(dtoInput, ServiceInOrder.class);
    }
}
