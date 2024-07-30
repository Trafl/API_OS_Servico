package com.os.service.domain.repository;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.WorkStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.scheduledDate BETWEEN :startDate AND :endDate")
    Page<Order> findByDate(Pageable pageable, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Integer countByStatus(@Param("status") WorkStatus status);

}
