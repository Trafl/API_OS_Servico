package com.os.service.domain.model.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class WorkData {

    private Long technicianId;

    private String technicianName;

    private LocalTime start;

    private LocalTime end;
}
