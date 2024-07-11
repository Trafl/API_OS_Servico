package com.os.service.domain.service;

import com.os.service.domain.model.GroupServices;
import com.os.service.domain.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface GroupSServices {

    public Page<GroupServices> getAllGroups(Pageable pageable);

    public GroupServices getGroupById(Long id);

    public GroupServices addGroup(GroupServices group);

    public GroupServices addServiceToGroup(Long id, Service service);

    public GroupServices updateGroup(Long id , GroupServices group);

    public void disabledGroupById(Long id);
}
