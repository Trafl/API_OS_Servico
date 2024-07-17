package com.os.service.api.order.DTO.generatorstatus;

import com.os.service.domain.model.order.Verification;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class MotorProtectDTO {

    private Verification motorProtect_baixaPressaOleo;

    private Verification motorProtect_altaTemperatura;

    private Verification motorProtect_ruidosOuVibracoesAnormais;
}
