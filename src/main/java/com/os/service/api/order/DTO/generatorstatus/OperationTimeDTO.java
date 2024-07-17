package com.os.service.api.order.DTO.generatorstatus;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class OperationTimeDTO {

    private String operationTime_faltaDeRede;

    private String operationTime_assimirCarga;

    private String operationTime_retornoDeRede;

    private String operationTime_resfriamentoGerador;
}
