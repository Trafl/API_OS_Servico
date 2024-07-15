package com.os.service.api.serviceInOrder.DTO.output;

import com.os.service.domain.model.order.Verification;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ServiceInOrderDTOOutput {

    private ServiceToServiceInOrderDTOOutput service;

    @Enumerated(EnumType.STRING)
    private Verification verificationBefore;

    private String urlPhotoBefore;

    @Enumerated(EnumType.STRING)
    private Verification verificationAfter;

    private String urlPhotoAfter;
}
