package com.os.service.domain.service.impl;

import com.os.service.domain.exception.ServiceNotFoundException;
import com.os.service.domain.model.Services;
import com.os.service.domain.repository.ServicesRepository;
import com.os.service.domain.service.ServiceServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ServiceServicesImpl implements ServiceServices {

    final private ServicesRepository repository;
    private String timestamp = LocalDateTime.now().toString();

    @Override
    public List<Services> getAllServices(){
        log.info("[{}] - [ServiceServices] Executing getAllService ", timestamp);
        return repository.findAll();
    }

    @Override
    public Services getServiceById(Long id) {
        log.info("[{}] - [ServiceServices] Executing getServiceById with id: {} ", timestamp, id);
        var savedService = repository.findById(id).orElseThrow(() -> new ServiceNotFoundException(
                String.format("Service with Id: %d not found in database.", id)
        ));

        log.info("[{}] - [ServiceServices] service found successful. id: {} ", timestamp, id);

        return savedService;
    }

    @Override
    public Services addService(Services service) {
        log.info("[{}] - [ServiceServices] Executing addService with body: {} ", timestamp, service);
        var savedService = repository.save(service);

        log.info("[{}] - [ServiceServices] service add successful. id: {} ", timestamp, savedService.getId());
        return savedService;
    }

    @Override
    public Services updateService(Long id, Services service) {
        log.info("[{}] - [ServiceServices] Executing updateService for Service id: {}. Body: {} ", timestamp, id, service);
        var savedService = getServiceById(id);

        savedService.setName(service.getName());
        savedService.setDescription(service.getDescription());

        savedService = repository.save(savedService);
        log.info("[{}] - [ServiceServices] service updated successful. id: {} ", timestamp, savedService.getId());
        return savedService;
    }

    @Override
    public void deleteServiceById(Long id) {
        //atento ao null ID
        //descobrir a exception do repository quando o Id n existe no banco
        log.info("[{}] - [ServiceServices] Executing deleteServiceById service id: {}", timestamp, id);
        getServiceById(id);
        repository.deleteById(id);

        log.info("[{}] - [ServiceServices] service deleted successful. id: {} ", timestamp, id);
    }
}
