package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.flat.SizeDtoFlat;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.SizeResponseDto;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SizeServiceImpl implements ISizeService {

    @Autowired
    private ISizeRepository repository;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseDto<SizeResponseDto> insert(SizeDtoFlat dto) {
        Optional<Size> sizeOptional = Optional.ofNullable(repository.findByName(dto.getName()));
        if(!sizeOptional.isEmpty()){
            throw new RepeatDataException("size này đã tồn tại");
        }
        Size newSize = dto.changeToSizeInsert();
        repository.save(newSize);

        return new ResponseDto<SizeResponseDto>(new SizeResponseDto(newSize, new ArrayList<>()));
    }

    @Override
    public ResponseDto<SizeResponseDto> update(SizeDtoFlat dto) {
        Optional<Size> sizeOptional = repository.findById(dto.getId());
        Optional<List<Product>> optionalProduct = Optional.ofNullable(productRepository.findBySizeId(dto.getId()));
        List<Product> listProduct= new ArrayList<>();

        if(sizeOptional.isEmpty()){
            throw new ResourceFoundException("size này Không tồn tại");
        }
        if(!optionalProduct.isEmpty()){
            listProduct = optionalProduct.get();
        }
        Size modifiedSize = dto.changeToSizeUpdate(sizeOptional.get());
        repository.save(modifiedSize);

        return new ResponseDto<SizeResponseDto>(new SizeResponseDto(modifiedSize, listProduct));
    }

//    change status => false. Stop using and stop selling products of this size
    @Override
    public ResponseDto<SizeResponseDto> delete(Integer id) {

        return null;
    }

    @Override
    public ResponseDto<SizeResponseDto> getById(Integer id) {
        Optional<Size> optional = repository.findById(id);
        Optional<List<Product>> optionalProduct = Optional.ofNullable(productRepository.findBySizeId(id));
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy size này");
        }
        List<Product> listProduct= new ArrayList<>();
        if(!optionalProduct.isEmpty()){
            listProduct = optionalProduct.get();
        }
        return new ResponseDto<SizeResponseDto>(new SizeResponseDto(optional.get(), listProduct));
    }

    @Override
    public ResponseDto<List<SizeResponseDto>> listAll() {
       List<Size> listSize = repository.findAll();
        List<SizeResponseDto> listResult = new ArrayList<>();
        if(listSize.isEmpty()){
            throw new ResourceFoundException("Danh sách size rỗng");
        }
        for (Size size : listSize) {
            Optional<List<Product>> optionalProduct = Optional.ofNullable(productRepository.findBySizeId(size.getId()));
            List<Product> listProduct= new ArrayList<>();
            if(!optionalProduct.isEmpty()){
                listProduct = optionalProduct.get();
            }
            listResult.add(new SizeResponseDto(size, listProduct));
        }
        return new ResponseDto<List<SizeResponseDto>>(listResult);
    }
}
