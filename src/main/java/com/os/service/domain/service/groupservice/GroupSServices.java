package com.os.service.domain.service.groupservice;

import com.os.service.domain.model.group_service.GroupServices;
import com.os.service.domain.model.service.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GroupSServices {

    public Page<GroupServices> getAllGroups(Pageable pageable);

    public GroupServices getGroupById(Long id);

    public GroupServices addGroup(GroupServices group);

    public GroupServices addServiceToGroup(Long id, Service service);

    public GroupServices updateGroup(Long id , GroupServices group);

    public void disabledGroupById(Long id);
}
