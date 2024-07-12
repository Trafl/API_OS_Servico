package com.os.service.domain.model.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long group_id;

    public Service(String name,String description ){
        this.name =name;
        this.description = description;
    }

    public Service(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
