package com.os.service.domain.services.groupservice;

import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.model.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupSServices {

     Page<GroupServices> getAllGroups(Pageable pageable);

     GroupServices getGroupById(Long id);

     Page<Service> getServicesByGroupId(Pageable pageable, Long groupId);

     GroupServices addGroup(GroupServices group);

     GroupServices addServiceToGroup(Long id, Service service);

     GroupServices updateGroup(Long id , GroupServices group);

     void disabledGroupById(Long id);
}
