package com.os.service.domain.services.serviceInOrder;

import com.os.service.domain.exception.ServiceInOrderNotFoundException;
import com.os.service.domain.model.order.serviceInOrder.ServiceInOrder;
import com.os.service.domain.repository.ServiceInOrderRepository;
import com.os.service.domain.services.aws.AwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class ServiceInOrderServiceImpl implements ServiceInOrderService {

    private final ServiceInOrderRepository serviceInOrderRepository;

    private final AwsService awsService;
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

    @Override
    public ServiceInOrder saveServiceInOrder(ServiceInOrder serviceInOrder) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing addServiceToOrder with body: {}", timestamp, serviceInOrder);

        var savedServiceInOrder = serviceInOrderRepository.save(serviceInOrder);

        log.info("[{}] - [ServiceInOrderServiceImpl] serviceInOrder add successful. id: {}", timestamp, savedServiceInOrder.getId());
        return savedServiceInOrder;
    }

    @Override
    public void deleteServiceInOrderById(Long id) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing deleteServiceById ServiceInOrder Id: {} ", timestamp, id);
        getServiceById(id);
        serviceInOrderRepository.deleteById(id);

        log.info("[{}] - [ServiceInOrderServiceImpl] serviceInOrder delete successful. id: {}", timestamp, id);

    }

    @Override
    @Transactional
    public void addPhotoBefore(Long id, MultipartFile file) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing addPhotoBefore ServiceInOrder Id: {} ", timestamp, id);

        var savedServiceInOrder = getServiceById(id);
        var urlPhoto = awsService.savePhotoInS3(file);

        savedServiceInOrder.setUrlPhotoBefore(urlPhoto);
        log.info("[{}] - [ServiceInOrderServiceImpl]  add Photo Before successful. ServiceInOrder Id: {} ", timestamp, id);
    }

    @Override
    @Transactional
    public void addPhotoAfter(Long id, MultipartFile file) {
        log.info("[{}] - [ServiceInOrderServiceImpl] Executing addPhotoAfter ServiceInOrder Id: {} ", timestamp, id);
        var savedServiceInOrder = getServiceById(id);
        var urlPhoto = awsService.savePhotoInS3(file);

        savedServiceInOrder.setUrlPhotoAfter(urlPhoto);
        log.info("[{}] - [ServiceInOrderServiceImpl]  add Photo After successful. ServiceInOrder Id: {} ", timestamp, id);
    }
}
