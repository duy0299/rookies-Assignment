package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Categories;
import com.rookies.assignment.data.repository.ICategoriesRepository;
import com.rookies.assignment.dto.flat.CategoriesDtoFlat;
import com.rookies.assignment.dto.response.CategoriesResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    @Autowired
    private ICategoriesRepository repository;

    @Override
    public ResponseDto<CategoriesResponseDto> insert(CategoriesDtoFlat dto) {
        Optional<Categories> optionalCategories = Optional.ofNullable(repository.findByName(dto.getName()));
        if(!optionalCategories.isEmpty()){
            throw new ResourceFoundException("Loại Trang Sức này đã tồn tại.");
        }
        Categories newCategories = repository.save(dto.changeToCategoriesInsert());
        return new ResponseDto<>(
                new CategoriesResponseDto(
                        newCategories
                )
        );
    }

    @Override
    public ResponseDto<CategoriesResponseDto> update(CategoriesDtoFlat dto) {
        Optional<Categories> optionalCategories = repository.findById(dto.getId());
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Loại trang sức này không tồn tại.");
        }
        Categories newCategories = repository.save(dto.changeToCategoriesUpdate(optionalCategories.get().getListModel()));
        return new ResponseDto<>(
                new CategoriesResponseDto(
                        newCategories
                )
        );
    }

    @Override
    public ResponseDto<CategoriesResponseDto> delete(int id) {
        Optional<Categories> optionalCategories = repository.findById(id);
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Loại trang sức này không tồn tại.");
        }
        repository.delete(optionalCategories.get());
        return new ResponseDto<>("thành công");
    }

    @Override
    public ResponseDto<CategoriesResponseDto> getById(int id) {
        Optional<Categories> optionalCategories = repository.findById(id);
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Loại trang sức này không tồn tại.");
        }
        return new ResponseDto<>(
                new CategoriesResponseDto(
                        optionalCategories.get()
                )
        );
    }

    @Override
    public ResponseDto<List<CategoriesResponseDto>> listAll() {
        List<CategoriesResponseDto> listResult = new ArrayList<>();
        Optional<List<Categories>> listOptional = Optional.ofNullable(repository.findAll());
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("danh sách rỗng.");
        }
//        Categories => Convert to CategoriesResponseDto => Add to listResult
        for (Categories categories: listOptional.get()) {
            listResult.add(new CategoriesResponseDto(categories));
        }
        return new ResponseDto<>(listResult);
    }
}
