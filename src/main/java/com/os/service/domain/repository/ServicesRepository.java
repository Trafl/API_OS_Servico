package com.os.service.domain.repository;

import com.os.service.domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Service, Long>{
}
