package com.os.service.api.order.DTO.generatorstatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GeneratorStatusDTO {

    private MotorProtectDTO motorProtect;

    private GeneratorProtectDTO generatorProtect;

    private OperationTimeDTO operationTime;
}
