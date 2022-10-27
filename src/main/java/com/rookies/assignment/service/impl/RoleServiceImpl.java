package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository repository;


    @Override
    public Role getByName(String name) {
        Optional<Role> optional = Optional.ofNullable(repository.findByName(name));
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy role này");
        }
        return optional.get();
    }

    @Override
    public Role getById(UUID id) {
        Optional<Role> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy role này");
        }
        return optional.get();
    }

    @Override
    public List<Role> listAll() {
        Optional<List<Role>> optional = Optional.ofNullable(repository.findAll());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        return optional.get();
    }
}
