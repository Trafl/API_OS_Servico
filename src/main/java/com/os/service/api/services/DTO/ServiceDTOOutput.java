package com.os.service.api.services.DTO;

import com.os.service.api.groupServices.DTO.output.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.output.GroupNameDTOOutput;
import com.os.service.domain.model.group_service.GroupServices;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServiceDTOOutput{

    private Long id;

    private String name;

    private String description;

    private GroupNameDTOOutput groupServices;
}
