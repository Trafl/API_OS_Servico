package com.os.service.domain.model.order.generatorstatus;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class GeneratorStatus {

    @Embedded
    private MotorProtect motorProtect;

    @Embedded
    private GeneratorProtect generatorProtect;

    @Embedded
    private OperationTime operationTime;
}
