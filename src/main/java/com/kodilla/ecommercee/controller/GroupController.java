package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin("*")
public class GroupController {

    private GroupService groupService;
    private GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups(){
        return ResponseEntity.ok(groupMapper.mapToGroupDtoToList(groupService.getAllGroups()));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.getGroup(groupId)));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        groupService.deleteGroup(groupId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long groupId, @RequestBody GroupDto groupDto) throws GroupNotFoundException {
        return ResponseEntity.ok(groupMapper.mapToGroupDto(groupService.updateGroup(groupId, groupDto.getName())));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup(groupMapper.mapToGroup(groupDto));
        return ResponseEntity.ok().build();
    }
}
