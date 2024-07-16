package com.os.service.api.order.DTO.workData;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
public class WorkDataDTOOutput {

    private LocalTime start;

    private LocalTime end;

}
