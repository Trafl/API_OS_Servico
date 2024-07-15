package com.os.service.domain.model.order.generatorstatus;

import com.os.service.domain.model.order.Verification;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class MotorProtect {

    private Verification motorProtect_baixaPressaOleo;

    private Verification motorProtect_altaTemperatura;

    private Verification motorProtect_ruidosOuVibracoesAnormais;
}
