package com.os.service.core.mapperconfig;

import org.modelmapper.ModelMapper;
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
