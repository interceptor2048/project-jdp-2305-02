package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.dto.GroupDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {

    public Group mapToGroup(final GroupDto groupDto){
        return new Group(
                groupDto.getId(),
                groupDto.getName()
        );
    }

    public GroupDto mapToGroupDto(final Group group){
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    public List<Group> mapToGroupToList(final List<GroupDto> groupDtoList){
        return groupDtoList.stream()
                .map(this::mapToGroup)
                .collect(Collectors.toList());
    }

    public List<GroupDto> mapToGroupDtoToList(final List<Group> groupList){
        return groupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
