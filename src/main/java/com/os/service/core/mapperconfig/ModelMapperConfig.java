package com.os.service.core.mapperconfig;

import com.os.service.api.order.DTO.endereco.EnderecoDTOOutput;
import com.os.service.api.order.DTO.output.OrderOneDTOOutput;
import com.os.service.domain.model.order.Endereco;
import com.os.service.domain.model.order.Order;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper config(){
        var modelMapper = new ModelMapper();
        return modelMapper;
    }
}
