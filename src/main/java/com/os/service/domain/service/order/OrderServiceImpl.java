package com.os.service.domain.service.order;

import com.os.service.domain.exception.OrderNotFoundException;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.ServiceInOrder;
import com.os.service.domain.model.order.WorkStatus;
import com.os.service.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private  String timestamp = LocalDateTime.now().toString();

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        log.info("[{}] - [OrderServiceImpl] Executing getAllOrders ", timestamp);
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getOneOrderById(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing getOneOrderById with id: {} ", timestamp, orderId);
        var savedOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(
                        String.format("Group with Id: %d not found in database.")));

        log.info("[{}] - [OrderServiceImpl] order found successful. id: {} ", timestamp, orderId);
        return savedOrder;
    }

    @Override
    public Order addOrder(Order order) {
        log.info("[{}] - [OrderServiceImpl] Executing addOrder with body: {} ", timestamp, order);
        var savedOrder = orderRepository.save(order);

        log.info("[{}] - [OrderServiceImpl] order add successful. id: {} ", timestamp, savedOrder.getId());
        return savedOrder;
    }

    @Override
    public Order addServiceToOrder(ServiceInOrder serviceInOrder) {
        return null;
    }

    @Override
    public void starOrder(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing starOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(orderInDb.getStatus().equals(WorkStatus.ABERTO)){
            orderInDb.starOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Started ", timestamp, orderId);
        }
    }

    @Override
    public void closeOrder(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing closeOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(orderInDb.getStatus().equals(WorkStatus.ANDAMENTO)){
            orderInDb.closeOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Closed ", timestamp, orderId);
        }
    }

    @Override
    public void cancelOrder(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing cancelOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(orderInDb.getStatus().equals(WorkStatus.ABERTO)||orderInDb.getStatus().equals(WorkStatus.ANDAMENTO)){
            orderInDb.closeOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Canceled ", timestamp, orderId);
        }
    }

    @Override
    public Order updateOrderById(Long orderId) {

        // fazer com o mapper

        return null;
    }

    @Override
    public void deleteOrderById(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing deleteOrderById group id: {}", timestamp, orderId);
        getOneOrderById(orderId);
        orderRepository.deleteById(orderId);
        log.info("[{}] - [OrderServiceImpl] order delete successful. id: {} ", timestamp, orderId);
    }
}
