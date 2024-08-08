package com.os.service.api.serviceInOrder.DTO.input;

import com.os.service.domain.model.order.Verification;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ServiceInOrderDTOInput {

    @Valid
    private ServiceToServiceInOrderDTOInput service;

    @NotNull
    private Verification verificationBefore;

    @NotNull
    private Verification verificationAfter;

}
