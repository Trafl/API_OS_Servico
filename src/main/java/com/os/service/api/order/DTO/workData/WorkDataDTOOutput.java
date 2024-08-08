package com.os.service.api.order.DTO.workData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class WorkDataDTOOutput {

    private LocalTime start;

    private LocalTime end;

}
