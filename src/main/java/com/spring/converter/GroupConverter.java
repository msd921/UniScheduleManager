package com.spring.converter;

import com.spring.dto.GroupDto;
import com.spring.model.Group;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupConverter {

    private final ModelMapper modelMapper;

    public GroupDto toDto(Group group) {
        return modelMapper.map(group, GroupDto.class);
    }

    public Group toEntity(GroupDto groupDto) {
        return modelMapper.map(groupDto, Group.class);
    }
}
