package com.os.service.api.order.DTO.output;

import com.os.service.api.order.DTO.endereco.EnderecoDTO;
import com.os.service.api.serviceInOrder.DTO.output.ServiceInOrderDTOOutput;
import com.os.service.domain.model.order.ClientType;
import com.os.service.domain.model.order.WorkStatus;
import com.os.service.domain.model.order.WorkType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderOneDTOOutput {

    private Long id;

    private WorkStatus status;

    private WorkType type;

    private LocalDateTime scheduledDate;

    private Long client_id;

    private ClientType client_type;

    private Long client_equipment_id;

    private Long technician_id;

    private EnderecoDTO endereco;

    private String escopoDosServicos;

    private List<ServiceInOrderDTOOutput> servicesInOrder;

}
