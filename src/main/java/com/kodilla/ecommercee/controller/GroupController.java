package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@CrossOrigin("*")
public class GroupController {

     List<GroupDto> groupDtoList = new ArrayList<GroupDto>(){
        {
            add(new GroupDto(1L, "Ubrania"));
            add(new GroupDto(2L, "Dodatki"));
            add(new GroupDto(3L, "Biżuteria"));
            add(new GroupDto(4L, "Obuwie"));
        }
    };

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups(){
      return ResponseEntity.ok(groupDtoList);
    }

    @GetMapping(value="{groupId}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId){
        for (GroupDto group : groupDtoList) {
            if (group.getGroupId().equals(groupId)){
                ResponseEntity.ok(group);
            }
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        for (GroupDto group : groupDtoList) {
            if (group.getGroupId().equals(groupId)){
                groupDtoList.remove(group);
                ResponseEntity.ok().build();
            }
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto groupDto) {
        for (GroupDto group : groupDtoList) {
            if (group.getGroupId().equals(groupDto.getGroupId())){
                group.getName().replaceFirst(group.getName(), groupDto.getName());
            }
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupDtoList.add(groupDto);
        ResponseEntity.ok().build();
    }
}
