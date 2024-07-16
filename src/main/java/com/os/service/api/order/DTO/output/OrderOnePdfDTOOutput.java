package com.os.service.api.order.DTO.output;

import com.os.service.api.order.DTO.workData.WorkDataDTOOutput;
import com.os.service.api.serviceInOrder.DTO.output.ServiceInOrderDTOOutput;
import com.os.service.domain.model.order.WorkData;
import com.os.service.domain.model.order.WorkStatus;
import com.os.service.domain.model.order.WorkType;
import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import jakarta.persistence.Embedded;
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
public class OrderOnePdfDTOOutput {

    private Long id;

    private WorkStatus status;

    private WorkType type;

    private LocalDateTime scheduledDate;

    private Long client_id;

    private Long technician_id;

    private String escopoDosServicos;

    private List<ServiceInOrderDTOOutput> servicesInOrder;

    private WorkDataDTOOutput workData;

    private GeneratorTest generatorTest;

    private GeneratorStatus generatorStatus;

    private String generalObservations;


}

