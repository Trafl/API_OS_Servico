package com.os.service.api.serviceInOrder.DTO.input;

import com.os.service.domain.model.order.Verification;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class ServiceInOrderDTOInput {

    private ServiceToServiceInOrderDTOInput service;

    @Enumerated(EnumType.STRING)
    private Verification verificationBefore;

    @Enumerated(EnumType.STRING)
    private Verification verificationAfter;

}
