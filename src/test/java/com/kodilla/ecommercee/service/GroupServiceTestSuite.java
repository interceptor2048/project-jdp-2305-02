package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class GroupServiceTestSuite {

    @Autowired
    private GroupService groupService;

    @Test
    public void testCreateGroup(){
        //Given
        Group group = new Group(null, "Test group");

        //When
        groupService.createGroup(group);
        Long groupId = group.getId();

        //Then
        assertEquals(1, groupService.getAllGroups().size());

        //CleanUp
        groupService.deleteGroup(groupId);
    }

    @Test
    public void testGetAllGroups(){
        //Given
        Group group1 = new Group(null, "Test group 1");
        Group group2 = new Group(null, "Test group 2");
        Group group3 = new Group(null, "Test group 3");

        //When
        groupService.createGroup(group1);
        groupService.createGroup(group2);
        groupService.createGroup(group3);
        List<Group> groupList = groupService.getAllGroups();
        Long group1Id = group1.getId();
        Long group2Id = group2.getId();
        Long group3Id = group3.getId();

        //Then
        assertEquals(3, groupList.size());

        //CleanUp
        groupService.deleteGroup(group1Id);
        groupService.deleteGroup(group2Id);
        groupService.deleteGroup(group3Id);
    }

    @Test
    public void testGetExistingGroup(){
        //Given
        Group group = new Group(null, "Test group");

        //When
        groupService.createGroup(group);
        Long groupId = group.getId();
        Group groupCreated = groupService.getGroup(groupId);

        //Then
        assertEquals("Test group", groupCreated.getName());

        //CleanUp
        groupService.deleteGroup(groupId);
    }

    @Test
    public void testGetNotExistingGroup(){
        //Given

        //When
        Long groupId = 99L;

        //Then
        assertThrows(GroupNotFoundException.class, () -> groupService.getGroup(groupId));
    }

    @Test
    public void testDeleteExistingGroup(){
        //Given
        Group group = new Group(null, "Test group");

        //When
        groupService.createGroup(group);
        int listSizeBeforeDelete = groupService.getAllGroups().size();
        Long groupId = group.getId();
        groupService.deleteGroup(groupId);
        int listSizeAfterDelete = groupService.getAllGroups().size();

        //Then
        assertEquals(1, listSizeBeforeDelete);
        assertEquals(0, listSizeAfterDelete);
    }

    @Test
    public void testDeleteNotExistingGroup(){
        //Given

        //When
        Long groupId = 99L;

        //Then
        assertThrows(GroupNotFoundException.class, () -> groupService.deleteGroup(groupId));
    }

    @Test
    public void testUpdateExistingGroup(){
        //Given
        Group groupBeforeUpdate = new Group(null, "Test group");

        //When
        groupService.createGroup(groupBeforeUpdate);
        Long groupId = groupBeforeUpdate.getId();
        Group groupAfterUpdate =groupService.updateGroup(groupId, "Group updated");

        //Then
        assertEquals("Test group", groupBeforeUpdate.getName());
        assertEquals("Group updated", groupAfterUpdate.getName());
        assertEquals(1, groupService.getAllGroups().size());

        //CleanUp
        groupService.deleteGroup(groupId);
    }

    @Test
    public void testUpdateNotExistingGroup(){
        //Given

        //When
        Long groupId = 99L;

        //Then
        assertThrows(GroupNotFoundException.class, () -> groupService.updateGroup(groupId, "Group updated"));
    }
}
