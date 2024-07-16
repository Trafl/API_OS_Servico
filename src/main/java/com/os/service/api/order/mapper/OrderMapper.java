package com.os.service.api.order.mapper;

import com.os.service.api.groupServices.DTO.output.GroupAllDTOOutput;
import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.order.DTO.output.OrderAllDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.api.order.DTO.output.OrderOnePdfDTOOutput;
import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.model.order.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final ModelMapper mapper;

    public Order toModel(OrderDTOInput dtoInput){
        return mapper.map(dtoInput, Order.class);
    }

    public OrderOneDTOOutput toDTOOne(Order order){
        return mapper.map(order, OrderOneDTOOutput.class);
    }

    public Page<OrderAllDTOOutput> mapPageToDTO(Page<Order> orders) {
        return orders.map(order -> mapper.map(order, OrderAllDTOOutput.class));
    }

    public OrderOnePdfDTOOutput toDTOPdf(Order order){
        return mapper.map(order, OrderOnePdfDTOOutput.class);
    }
}
