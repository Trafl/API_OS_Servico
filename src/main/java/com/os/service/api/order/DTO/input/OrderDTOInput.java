package com.os.service.api.order.DTO.input;

import com.os.service.api.order.DTO.endereco.EnderecoDTOInput;
import com.os.service.domain.model.order.ClientType;
import com.os.service.domain.model.order.WorkType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTOInput {

    @NotNull
    private WorkType type;

    @NotNull
    private LocalDateTime scheduledDate;

    @NotNull
    private Long client_id;

    @NotNull
    private ClientType client_type;

    @NotNull
    private Long client_equipment_id;

    @NotNull
    private Long technician_id;

    @Valid
    private EnderecoDTOInput endereco;

    @NotBlank
    private String escopoDosServicos;
}
