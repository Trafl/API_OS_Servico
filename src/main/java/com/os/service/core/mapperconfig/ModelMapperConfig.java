package com.os.service.core.mapperconfig;

import com.os.service.api.order.DTO.input.OrderDTOInput;
import com.os.service.api.serviceInOrder.DTO.input.ServiceInOrderDTOInput;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import com.os.service.domain.model.service.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper config(){
        var mapper = new ModelMapper();

        return mapper;
    }
}
