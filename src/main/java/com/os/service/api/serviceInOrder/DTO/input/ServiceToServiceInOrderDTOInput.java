package com.os.service.api.serviceInOrder.DTO.input;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceToServiceInOrderDTOInput {

    @NotNull
    private Long id;
}
