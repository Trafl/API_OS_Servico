package com.os.service.api.groupServices.DTO.output;

import com.os.service.api.services.DTO.ServiceDTOOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GroupOneDTOOutput{

    private Long id;

    private String name;

    private List<ServiceDTOOutput> services;
}
