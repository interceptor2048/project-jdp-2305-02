package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class GroupRepositoryTestSuite {
    @Autowired
    GroupRepository groupRepository;
    @Test
    void testSaveRetrieveGroup() {
        //Given
        Group group = new Group(null, "test name");

        //When
        groupRepository.save(group);
        Long id = group.getId();
        Optional<Group> retrievedGroup = groupRepository.findById(id);

        //Then
        assertTrue(retrievedGroup.isPresent());
        assertEquals(group.getName(), retrievedGroup.get().getName());

        //CleanUp
        groupRepository.deleteById(id);
    }
    @Test
    void testDeleteGroup() {
        //Given
        Group group = new Group(null, "test name");
        groupRepository.save(group);
        Long id = group.getId();

        //When
        groupRepository.deleteById(id);
        Optional<Group> retrievedGroup = groupRepository.findById(id);

        //TODO- also check if products related to deleted group are also deleted. To complete when Group entity finished
        // and merged; and relations between Product and Group entity estabilished

        //Then
        Assertions.assertFalse(retrievedGroup.isPresent());

        //CleanUp
        try {
            groupRepository.deleteById(id);
        } catch (Exception e) {

        }
    }
    @Test
    void testFindAllGroups() {
        //Given
        Group group1 = new Group(null, "test name 1");
        Group group2 = new Group(null, "test name 2");
        Group group3 = new Group(null, "test name 3");
        groupRepository.save(group1);
        groupRepository.save(group2);
        groupRepository.save(group3);
        Long id1 = group1.getId();
        Long id2 = group2.getId();
        Long id3 = group3.getId();

        //When
        List<Group> retrievedListOfGroups = groupRepository.findAll();

        //Then
        Assertions.assertEquals(3, retrievedListOfGroups.size());

        //CleanUp
        groupRepository.deleteById(id1);
        groupRepository.deleteById(id2);
        groupRepository.deleteById(id3);
    }
}