package com.os.service.api.groupServices.mapper;

import com.os.service.api.groupServices.DTO.output.GroupAllDTOOutput;
import com.os.service.api.groupServices.DTO.input.GroupDTOInput;
import com.os.service.api.groupServices.DTO.output.GroupDTONameOutput;
import com.os.service.api.groupServices.DTO.output.GroupOneDTOOutput;
import com.os.service.domain.model.group_service.GroupServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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
