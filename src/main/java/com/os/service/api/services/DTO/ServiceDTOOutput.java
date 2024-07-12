package com.os.service.api.services.DTO;

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

    private Long group_id;
}
