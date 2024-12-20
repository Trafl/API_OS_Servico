package com.os.service.api.order.DTO.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.os.service.domain.model.order.WorkStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderAllDTOOutput {

    private Long id;

    @Enumerated(EnumType.STRING)
    private WorkStatus status;

    private Long client_id;

    private Long technician_id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime scheduledDate;

}
