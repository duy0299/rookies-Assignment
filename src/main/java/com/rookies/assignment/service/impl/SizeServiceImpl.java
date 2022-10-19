package com.rookies.assignment.dto.response.impl;

import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements ISizeService {

    @Autowired
    private ISizeRepository repository;


    @Override
    public ResponseDto<Size> insert(SizeDtoFlat dto) {
        Optional<Size> sizeOptional = Optional.ofNullable(repository.findByName(dto.getName()));
        if(!sizeOptional.isEmpty()){
            throw new ResourceFoundException("size này đã tồn tại");
        }
        Size newSize = dto.changeToSizeInsert();
        repository.save(newSize);

        return new ResponseDto<Size>(newSize);
    }

    @Override
    public ResponseDto<Size> update(SizeDtoFlat dto) {
        Optional<Size> sizeOptional = repository.findById(dto.getId());
        if(sizeOptional.isEmpty()){
            throw new ResourceFoundException("size này Không tồn tại");
        }
        Size modifiedSize = dto.changeToSizeUpdate(sizeOptional.get().getListProduct());
        repository.save(modifiedSize);

        return new ResponseDto<Size>(modifiedSize);
    }

//    change status => false. Stop using and stop selling products of this size
    @Override
    public ResponseDto<Size> delete(Integer id) {

        return null;
    }

    @Override
    public ResponseDto<Size> getById(Integer id) {
        Optional<Size> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy size này");
        }
        return new ResponseDto<Size>(optional.get());
    }

    @Override
    public ResponseDto<List<Size>> listAll() {
        Optional<List<Size>> listOptional = Optional.ofNullable(repository.findAll());
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách size rỗng");
        }
        return new ResponseDto<List<Size>>(listOptional.get());
    }
}
