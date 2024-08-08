package com.os.service.api.order.mapper;

import com.os.service.api.order.DTO.generatorstatus.GeneratorStatusDTO;
import com.os.service.api.order.DTO.generatortest.GeneratorTestDTO;
import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.order.DTO.output.OrderAllDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.api.order.DTO.output.OrderOnePdfDTOOutput;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public GeneratorStatus toStatusModel(GeneratorStatusDTO generatorStatusDTO){
        return mapper.map(generatorStatusDTO, GeneratorStatus.class);
    }

    public GeneratorTest toTesteModel(GeneratorTestDTO generatorStatusDTO){
        return mapper.map(generatorStatusDTO, GeneratorTest.class);
    }

    public List<OrderAllDTOOutput> toCollectionDto(List<Order> list){
        return list.stream().map(order -> mapper.map(order, OrderAllDTOOutput.class)).toList();
    }
}
