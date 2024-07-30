package com.os.service.domain.services.order;

import com.os.service.domain.exception.OrderFinishedOrCanceledException;
import com.os.service.domain.exception.OrderNotFoundException;
import com.os.service.domain.exception.OrderWrongStatusException;
import com.os.service.domain.model.order.Order;
import com.os.service.domain.model.order.WorkData;
import com.os.service.domain.model.order.generatorstatus.GeneratorStatus;
import com.os.service.domain.model.order.generatortest.GeneratorTest;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import com.os.service.domain.model.order.WorkStatus;
import com.os.service.domain.repository.OrderRepository;
import com.os.service.domain.services.service.ServiceServices;
import com.os.service.domain.services.serviceInOrder.ServiceInOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final ServiceServices serviceServices;

    private final OrderRepository orderRepository;

    private final ServiceInOrderService serviceInOrderService;
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
                        String.format("Order with Id: %d not found in database.", orderId)));

        log.info("[{}] - [OrderServiceImpl] order found successful. id: {} ", timestamp, orderId);
        return savedOrder;
    }

    @Override
    public Page<Order> GetOrderByDate( Pageable pageable, LocalDateTime starDay, LocalDateTime endDay) {
        log.info("[{}] - [OrderServiceImpl] Executing GetOrderByDate between {} and {} ", timestamp, starDay, endDay);
        return orderRepository.findByDate(pageable, starDay, endDay);
    }

    @Override
    public Integer countOrderByStatus() {
        return orderRepository.countByStatus(WorkStatus.ABERTO);
    }

    @Override
    public Order addOrder(Order order) {
        log.info("[{}] - [OrderServiceImpl] Executing addOrder with body: {} ", timestamp, order);
        var savedOrder = orderRepository.save(order);

        log.info("[{}] - [OrderServiceImpl] order add successful. id: {} ", timestamp, savedOrder.getId());
        return savedOrder;
    }

    @Override
    public Order addServiceToOrder(Long orderId, ServiceInOrder serviceInOrder){
        log.info("[{}] - [OrderServiceImpl] Executing addServiceToOrder Id Order: {}, ServiceInOrder: {} ", timestamp, orderId, serviceInOrder);

        var order  = getOneOrderById(orderId);

        if(order.getStatus().equals(WorkStatus.FINALIZADO) || order.getStatus().equals(WorkStatus.CANCELADO )){
            throw new OrderFinishedOrCanceledException(
                    String.format("Order with Id: %d is completed or canceled", orderId));
        }

        serviceInOrder.setOrder(order);

        log.info("[{}] - [OrderServiceImpl] Linked Order to ServiceInOrder", timestamp);


        var service = serviceServices.getServiceById(serviceInOrder.getService().getId());

        serviceInOrder.setService(service);

        log.info("[{}] - [OrderServiceImpl] Linked Service to ServiceInOrder", timestamp);


        var savedServiceInOrder =  serviceInOrderService.saveServiceInOrder(serviceInOrder);

        order.getServicesInOrder().add(savedServiceInOrder);

        log.info("[{}] - [OrderServiceImpl] Add ServiceInOrder Id: {},  after save, in Order with Id: {} ", timestamp,savedServiceInOrder.getId(), orderId);

        var orderWithService = orderRepository.save(order);

        log.info("[{}] - [OrderServiceImpl] ServiceInOrder with Id: {} add with successful to Order with Id: {} ", timestamp,savedServiceInOrder.getId(), orderWithService.getId());

        return orderWithService;
    }

    @Override
    @Transactional
    public void starOrder(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing starOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(orderInDb.getStatus().equals(WorkStatus.ABERTO)){
            orderInDb.setWorkData(new WorkData());
            orderInDb.starOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Started ", timestamp, orderId);
        }else{
        throw new OrderWrongStatusException(
                String.format("Order Id: %d is completed or in progress", orderId));
    }
    }

    @Override
    @Transactional
    public void closeOrder(Long orderId, String generalObservations) {
        log.info("[{}] - [OrderServiceImpl] Executing closeOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(generalObservations == null){
            generalObservations = "";
        }

        if(orderInDb.getStatus().equals(WorkStatus.ANDAMENTO)){
            orderInDb.setGeneralObservations(generalObservations);
            orderInDb.closeOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Closed ", timestamp, orderId);
        }else{
            throw new OrderWrongStatusException(
                    String.format("Order: Id %d not start", orderId));
        }
    }

    @Override
    @Transactional
    public void cancelOrder(Long orderId) {
        log.info("[{}] - [OrderServiceImpl] Executing cancelOrder with Id Order:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);

        if(orderInDb.getStatus().equals(WorkStatus.ABERTO)||orderInDb.getStatus().equals(WorkStatus.ANDAMENTO)){
            orderInDb.cancelOrder();
            log.info("[{}] - [OrderServiceImpl] Id Order:{} Canceled ", timestamp, orderId);
        }else{
            throw new OrderWrongStatusException(
                    String.format("Order Id: %d is finalized, cannot cancel", orderId));
        }
    }

    @Override
    @Transactional
    public void addGeneratorTestToOrder(Long orderId, GeneratorTest generatorTest){
        log.info("[{}] - [OrderServiceImpl] Executing addGeneratorTestToOrder with Id:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);
        orderInDb.setGeneratorTest(generatorTest);
        log.info("[{}] - [OrderServiceImpl] Add GeneratorTest To Order with Id:{} ", timestamp, orderId);
    }

    @Override
    @Transactional
    public void addGeneratorStatusToOrder(Long orderId, GeneratorStatus generatorStatus ){
        log.info("[{}] - [OrderServiceImpl] Executing addGeneratorStatus with Id:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);
        orderInDb.setGeneratorStatus(generatorStatus);
        log.info("[{}] - [OrderServiceImpl] Add GeneratorStatus To Order with Id:{} ", timestamp, orderId);
    }

    @Override
    @Transactional
    public void addGeneralObservationsToOrder(Long orderId, String generalObservations ){
        log.info("[{}] - [OrderServiceImpl] Executing addGeneralObservations with Id:{} ", timestamp, orderId);
        var orderInDb = getOneOrderById(orderId);
        orderInDb.setGeneralObservations(generalObservations);
        log.info("[{}] - [OrderServiceImpl] Add GeneralObservations to Order with Id:{} ", timestamp, orderId);
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
        log.info("[{}] - [OrderServiceImpl] order delete successful. id: {}", timestamp, orderId);
    }
}
