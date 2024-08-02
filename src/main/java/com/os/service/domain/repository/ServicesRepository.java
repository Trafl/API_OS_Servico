package com.os.service.domain.repository;

import com.os.service.domain.model.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServicesRepository extends JpaRepository<Service, Long>{

    @Query("SELECT s FROM Service s WHERE s.group_services.id = :groupId")
    Page<Service> findServicesByGroupId(Pageable pageable, @Param("groupId") Long groupId);
}

