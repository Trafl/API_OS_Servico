package com.os.service.domain.repository;

import com.os.service.domain.model.group_service.GroupServices;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GroupServicesRepository extends JpaRepository<GroupServices, Long>{

    @Modifying
    @Transactional
    @Query("UPDATE GroupServices e SET e.deleted = true WHERE e.id = :id")
    void softDelete(Long id);

    @Query("SELECT e FROM GroupServices e WHERE e.deleted = false")
    Page<GroupServices> findAllGroupsActives(Pageable pageable);
}
