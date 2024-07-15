package com.os.service.domain.services.serviceInOrder;

import com.os.service.domain.exception.ServiceInOrderNotFoundException;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import com.os.service.domain.repository.ServiceInOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class ServiceInOrderServiceImpl implements ServiceInOrderService {

    private final ServiceInOrderRepository serviceInOrderRepository;
    private String timestamp = LocalDateTime.now().toString();

    @Override
    public ServiceInOrder getServiceById(Long id) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing getServiceById with id: {} ", timestamp, id);

        var savedServiceInOrder = serviceInOrderRepository.findById(id)
                .orElseThrow(() -> new ServiceInOrderNotFoundException(
                        String.format("ServiceInOrder with Id: %d not found in database.", id)
                ));

        log.info("[{}] - [ServiceInOrderServiceImpl] service in order found successful. id: {} ", timestamp, id);

        return savedServiceInOrder;
    }

    //Adicionar os serviços na ordem e depois abrir eles para add a foto
    // add serviço da AWS para salvar as fotos

    public ServiceInOrder addPhotoToService(Long id, ServiceInOrder serviceInOrder){

        var savedServiceInOrder = getServiceById(id);

        if(serviceInOrder.getUrlPhotoAfter().equals(null))
        savedServiceInOrder.setUrlPhotoBefore(serviceInOrder.getUrlPhotoBefore());

        savedServiceInOrder.setUrlPhotoAfter(serviceInOrder.getUrlPhotoAfter());

        return null;
    }


    @Override
    public ServiceInOrder saveServiceInOrder(ServiceInOrder serviceInOrder) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing addServiceToOrder with body: {} ", timestamp, serviceInOrder);

        var savedServiceInOrder = serviceInOrderRepository.save(serviceInOrder);

        log.info("[{}] - [ServiceInOrderServiceImpl] serviceInOrder add successful. id: {} {} ", timestamp, savedServiceInOrder.getId());
        return savedServiceInOrder;
    }

    @Override
    public void deleteServiceInOrderById(Long id) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing deleteServiceById Service Id: {} ", timestamp, id);
        getServiceById(id);
        serviceInOrderRepository.deleteById(id);

        log.info("[{}] - [ServiceInOrderServiceImpl] serviceInOrder delete successful. id: {}", timestamp, id);

    }
}
