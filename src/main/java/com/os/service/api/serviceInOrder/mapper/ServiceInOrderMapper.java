package com.os.service.api.serviceInOrder.mapper;

import com.os.service.api.serviceInOrder.DTO.input.ServiceInOrderDTOInput;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import com.os.service.domain.model.service.Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceInOrderMapper {

    private final ModelMapper mapper;
    public ServiceInOrder toModel(ServiceInOrderDTOInput dtoInput){
        return mapper.map(dtoInput, ServiceInOrder.class);
    }

//    private void config(){
//        TypeMap<ServiceInOrder, ServiceInOrderDTOInput> serviceInOrderToDTOMap = mapper.createTypeMap(ServiceInOrder.class, ServiceInOrderDTOInput.class);
//        serviceInOrderToDTOMap.addMappings(mapperr -> {
//            mapperr.map(src -> src.getService().getId(), (dest, v) -> dest.getService().setId((Long) v));
//            mapperr.map(ServiceInOrder::getVerificationBefore, ServiceInOrderDTOInput::setVerificationBefore);
//            mapperr.map(ServiceInOrder::getVerificationAfter, ServiceInOrderDTOInput::setVerificationAfter);
//        });
//
//        TypeMap<ServiceInOrderDTOInput, ServiceInOrder> dtoToServiceInOrderMap = mapper.createTypeMap(ServiceInOrderDTOInput.class, ServiceInOrder.class);
//        dtoToServiceInOrderMap.addMappings(mapperr -> {
//            mapperr.map(src -> {
//                Service service = new Service();
//                service.setId(src.getService().getId());
//                return service;
//            }, ServiceInOrder::setService);
//            mapperr.map(ServiceInOrderDTOInput::getVerificationBefore, ServiceInOrder::setVerificationBefore);
//            mapperr.map(ServiceInOrderDTOInput::getVerificationAfter, ServiceInOrder::setVerificationAfter);
//        });
//
//    }
}
