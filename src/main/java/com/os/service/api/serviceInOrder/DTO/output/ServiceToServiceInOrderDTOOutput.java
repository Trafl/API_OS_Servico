package com.os.service.api.serviceInOrder.DTO.output;


import com.os.service.api.groupServices.DTO.output.GroupNameDTOOutput;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceToServiceInOrderDTOOutput {

    private Long id;

    private String name;

    private GroupNameDTOOutput group;
}
