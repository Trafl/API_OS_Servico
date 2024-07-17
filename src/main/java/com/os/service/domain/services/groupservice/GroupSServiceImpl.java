package com.os.service.domain.services.groupservice;

import com.os.service.domain.exception.GroupServiceNotFoundException;
import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.model.service.Service;
import com.os.service.domain.repository.GroupServicesRepository;
import com.os.service.domain.services.service.ServiceServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;

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
    public GroupServices getGroupById(Long groupId) {
        log.info("[{}] - [GroupSServiceImpl] Executing getGroupById with id: {} ", timestamp, groupId);

        var savedGroup = repository.findById(groupId).orElseThrow(() -> new GroupServiceNotFoundException(
                String.format("Group with Id: %d not found in database.", groupId)
        ));

        log.info("[{}] - [GroupSServiceImpl] group found successful. id: {} ", timestamp, groupId);
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
    public GroupServices addServiceToGroup(Long groupId, Service service) {
        log.info("[{}] - [GroupSServiceImpl] Executing addServiceToGroup. Group id: {}  service: {} ", timestamp,groupId, service);

        var savedGroup = getGroupById(groupId);

        var savedService = serviceS.addService(service);

        savedService.setGroup_services(savedGroup);
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
    public void disabledGroupById(Long groupId) {
        //atento ao null ID
        //descobrir a exception do repository quando o Id n existe no banco
        log.info("[{}] - [GroupSServiceImpl] Executing deleteGroupById group id: {}", timestamp, groupId);
        getGroupById(groupId);
        repository.softDelete(groupId);

        log.info("[{}] - [GroupSServiceImpl] group disabled successful. id: {} ", timestamp, groupId);

    }
}
