package com.os.service.domain.service.impl;

import com.os.service.domain.exception.GroupServiceNotFoundException;
import com.os.service.domain.exception.ServiceNotFoundException;
import com.os.service.domain.model.GroupServices;
import com.os.service.domain.model.Service;
import com.os.service.domain.repository.GroupServicesRepository;
import com.os.service.domain.service.GroupSServices;
import com.os.service.domain.service.ServiceServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Log4j2
public class GroupSServiceImpl implements GroupSServices {

    final private GroupServicesRepository repository;

    final private ServiceServices serviceS;

    private String timestamp = LocalDateTime.now().toString();

    @Override
    public Page<GroupServices> getAllGroups(Pageable pageable) {
        log.info("[{}] - [GroupSServiceImpl] Executing getAllGroups ", timestamp);

        return repository.findAllGroupsActives(pageable);

    }

    @Override
    public GroupServices getGroupById(Long id) {
        log.info("[{}] - [GroupSServiceImpl] Executing getGroupById with id: {} ", timestamp, id);

        var savedGroup = repository.findById(id).orElseThrow(() -> new GroupServiceNotFoundException(
                String.format("Group with Id: %d not found in database.", id)
        ));

        log.info("[{}] - [GroupSServiceImpl] group found successful. id: {} ", timestamp, id);
        return savedGroup;
    }

    @Override
    public GroupServices addGroup(GroupServices group) {
        log.info("[{}] - [GroupSServiceImpl] Executing addGroup with body: {} ", timestamp, group);
        var savedGroup = repository.save(group);

        log.info("[{}] - [GroupSServiceImpl] group add successful. id: {} ", timestamp, savedGroup.getId());
        return savedGroup;
    }

    @Override
    @Transactional
    public GroupServices addServiceToGroup(Long id, Service service) {
        log.info("[{}] - [GroupSServiceImpl] Executing addServiceToGroup. Group id: {}  service: {} ", timestamp,id, service);

        var savedGroup = getGroupById(id);

        var savedService = serviceS.addService(service);

        savedService.setGroup_id(savedGroup.getId());
        savedGroup.getServices().add(savedService);

        serviceS.addService(service);

        var newGroup = repository.save(savedGroup);

        return newGroup;
    }

    @Override
    public GroupServices updateGroup(Long id, GroupServices group) {
        log.info("[{}] - [GroupSServiceImpl] Executing updateGroup for Group id: {}. Body: {} ", timestamp, id, group);

        var savedGroup = getGroupById(id);

        savedGroup.setName(group.getName());

        savedGroup = repository.save(savedGroup);
        log.info("[{}] - [GroupSServiceImpl] group updated successful. id: {} ", timestamp, savedGroup.getId());
        return savedGroup;
    }

    @Override
    public void disabledGroupById(Long id) {
        //atento ao null ID
        //descobrir a exception do repository quando o Id n existe no banco
        log.info("[{}] - [GroupSServiceImpl] Executing deleteGroupById group id: {}", timestamp, id);
        getGroupById(id);
        repository.softDelete(id);

        log.info("[{}] - [GroupSServiceImpl] group disabled successful. id: {} ", timestamp, id);

    }
}
