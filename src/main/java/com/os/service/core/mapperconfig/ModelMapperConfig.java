package com.os.service.core.mapperconfig;

import com.os.service.api.groupServices.DTO.GroupOneDTOOutput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.domain.model.GroupServices;
import com.os.service.domain.model.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper config(){
        var mapper = new ModelMapper();

        return mapper;
    }
}
