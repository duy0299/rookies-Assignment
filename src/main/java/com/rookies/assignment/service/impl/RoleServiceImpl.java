package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Role;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.repository.IRoleRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.SizeResponseDto;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IRoleService;
import com.rookies.assignment.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
