package com.os.service.domain.services.service;

import com.os.service.domain.exception.ServiceNotFoundException;
import com.os.service.domain.model.service.Service;
import com.os.service.domain.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Log4j2
public class ServiceServicesImpl implements ServiceServices {

    final private ServicesRepository repository;
    private String timestamp = LocalDateTime.now().toString();

    @Override
    public Page<Service> getAllServices(Pageable pageable){
        log.info("[{}] - [ServiceServicesImpl] Executing getAllService ", timestamp);
        return repository.findAll(pageable);
    }

    @Override
    public Service getServiceById(Long id) {
        log.info("[{}] - [ServiceServicesImpl] Executing getServiceById with id: {} ", timestamp, id);
        var savedService = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(
                String.format("Service with Id: %d not found in database.", id)
        ));

        log.info("[{}] - [ServiceServicesImpl] service found successful. id: {} ", timestamp, id);

        return savedService;
    }

    @Override
    public Service addService(Service service) {
        log.info("[{}] - [ServiceServicesImpl] Executing addService with body: {} ", timestamp, service);
        var savedService = repository.save(service);

        log.info("[{}] - [ServiceServicesImpl] service add successful. id: {} ", timestamp, savedService.getId());
        return savedService;
    }

    @Override
    public Service updateService(Long id, Service service) {
        log.info("[{}] - [ServiceServicesImpl] Executing updateService for Service id: {}. Body: {} ", timestamp, id, service);
        var savedService = getServiceById(id);

        savedService.setName(service.getName());
        savedService.setDescription(service.getDescription());

        savedService = repository.save(savedService);
        log.info("[{}] - [ServiceServicesImpl] service updated successful. id: {} ", timestamp, savedService.getId());
        return savedService;
    }

    @Override
    public void deleteServiceById(Long id) {
        //atento ao null ID
        //descobrir a exception do repository quando o Id n existe no banco
        log.info("[{}] - [ServiceServicesImpl] Executing deleteServiceById service id: {}", timestamp, id);
        getServiceById(id);
        repository.deleteById(id);

        log.info("[{}] - [ServiceServicesImpl] service deleted successful. id: {} ", timestamp, id);
    }
}
