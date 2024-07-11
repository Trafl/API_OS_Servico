package com.os.service.api.groupServices.mapper;

import com.os.service.api.groupServices.DTO.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.GroupDTOInput;
import com.os.service.api.groupServices.DTO.GroupDTONameOutput;
import com.os.service.api.groupServices.DTO.GroupOneDTOOutput;
import com.os.service.api.services.DTO.ServiceDTOOutput;
import com.os.service.api.services.mapper.ServiceMapper;
import com.os.service.domain.model.GroupServices;
import com.os.service.domain.model.Service;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupMapper {

    private final ModelMapper mapper;

    public GroupOneDTOOutput getOneDto(GroupServices groupServices) {
        return mapper.map(groupServices, GroupOneDTOOutput.class);
    }

    public GroupDTONameOutput toDTONameOutput(GroupServices groupServices){
        return mapper.map(groupServices, GroupDTONameOutput.class);
    }

    public Page<GroupAllDTOOutput> mapPageToDTO(Page<GroupServices> groups) {
        return groups.map(group -> mapper.map(group, GroupAllDTOOutput.class));
    }

    public GroupServices toModel(GroupDTOInput groupDTOOutput){
        return mapper.map(groupDTOOutput, GroupServices.class);
    }
}
