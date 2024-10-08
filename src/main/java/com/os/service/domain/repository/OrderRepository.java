package com.os.service.domain.repository;

import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.WorkStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.scheduledDate BETWEEN :startDate AND :endDate")
    Page<Order> findByDate(Pageable pageable, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") WorkStatus status);

    @Query("SELECT COUNT(o) FROM Order o")
    Long countTotal();

    @Override
    @Query("SELECT o FROM Order o ORDER BY o.id DESC")
     Page<Order> findAll(Pageable pageable);
}
