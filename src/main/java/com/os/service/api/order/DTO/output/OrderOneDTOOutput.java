package com.os.service.api.order.DTO.output;

import com.os.service.api.serviceInOrder.DTO.output.ServiceInOrderDTOOutput;
import com.os.service.domain.model.order.WorkStatus;
import com.os.service.domain.model.order.WorkType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    @Enumerated(EnumType.STRING)
    private WorkType type;

    private LocalDateTime scheduledDate;

    private Long client_id;

    private Long technician_id;

    private String escopoDosServicos;

    private List<ServiceInOrderDTOOutput> servicesInOrder;

}
