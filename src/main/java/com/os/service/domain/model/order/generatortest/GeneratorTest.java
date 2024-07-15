package com.os.service.domain.model.order.generatortest;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class GeneratorTest {

    @Embedded
    private Gmg gmg;

    @Embedded
    private Rede rede;

    @Embedded
    private Corrente corrente;
}
