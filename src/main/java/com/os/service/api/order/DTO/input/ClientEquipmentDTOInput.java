package com.os.service.api.order.DTO.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientEquipmentDTOInput {
    private Long id;
    private String tipo;
    private EquipamentoDTOInput equipamento;
    private Long idCliente;

    @JsonProperty("horimetro_atual")
    private Float horimetro_atual;

    @JsonProperty("KWH_atual")
    private Float KWH_atual;

}
