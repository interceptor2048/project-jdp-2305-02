package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/groups")
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

    @GetMapping(value = "{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long groupId){
        for (GroupDto group : groupDtoList) {
            if (group.getId().equals(groupId)){
                return ResponseEntity.ok(group);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        for (GroupDto group : groupDtoList) {
            if (group.getId().equals(groupId)){
                groupDtoList.remove(group);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value="{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> updateGroup(@PathVariable("id") Long groupId, @RequestBody GroupDto groupDto) {
        for (GroupDto group : groupDtoList) {
            if (groupId.equals(group.getId())){
                groupDtoList.remove(group);
                groupDtoList.add(groupDto);
                return ResponseEntity.ok(groupDto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createGroup(@RequestBody GroupDto groupDto) {
        groupDtoList.add(groupDto);
        return ResponseEntity.ok().build();
    }
}
