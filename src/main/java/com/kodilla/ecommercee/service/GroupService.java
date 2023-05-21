package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

    public void createGroup(Group group) {
        groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroup(final Long id) {
        return groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
    }

    public void deleteGroup(final Long id) {
        groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        groupRepository.deleteById(id);
    }

    public Group updateGroup(final Long id, String name) {
        Group toUpdate = groupRepository.findById(id).orElseThrow(GroupNotFoundException::new);
        toUpdate.setName(name);
        return groupRepository.save(toUpdate);
    }
}
