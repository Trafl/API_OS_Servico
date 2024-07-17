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
public class GeneratorProtectDTO {

    private Verification generatorProtect_tensao;

    private Verification generatorProtect_frequencia;

    private Verification generatorProtect_nivelTanqueDiesel;
}