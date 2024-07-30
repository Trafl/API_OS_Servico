package com.os.service.domain.repository;

import com.os.service.domain.model.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.scheduledDate BETWEEN :startDate AND :endDate")
    Page<Order> findByDate(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o FROM Order o WHERE o.scheduledDate BETWEEN :startDate AND :endDate")
    List<Order> findByDateAndClientName(LocalDateTime startDate, LocalDateTime endDate);

}
