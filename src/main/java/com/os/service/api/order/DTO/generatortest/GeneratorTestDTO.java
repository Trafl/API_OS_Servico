package com.os.service.api.order.DTO.generatortest;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class GeneratorTestDTO {

    private GmgDTO gmg;

    private RedeDTO rede;

    private CorrenteDTO corrente;
}
