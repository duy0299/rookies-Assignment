package com.rookies.assignment.service.impl;


import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateAvatarDto;
import com.rookies.assignment.dto.request.ProductRequestUpdateDto;
import com.rookies.assignment.dto.response.ProductResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.MethodNotAllowedException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository repository;
    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    private IProductModelRepository modelRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Value("${amazonProperties.folderSaveProduct}")
    private String folderName;


    @Override
    public ResponseDto<ProductResponseDto> insert(ProductRequestInsertDto dto) {
        String urlAvatar = "";
        Optional<ProductModel> modelOptional = modelRepository.findById(dto.getModelID());
        Optional<Size> sizeOptional = sizeRepository.findById(dto.getSizeID());
//      Validate
        validateInsert(modelOptional, sizeOptional, dto);
//      up file image to Amazon s3
        try {
            urlAvatar     = amazonClient.uploadFile(dto.getFileAvatar(), folderName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
//      create Product Entity
        Product newProduct =  repository.save(
                dto.changeToProduct(sizeOptional.get(), modelOptional.get(), urlAvatar )
        );
        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(newProduct));
    }

    @Override
    public ResponseDto<ProductResponseDto> update(ProductRequestUpdateDto dto) {
        if (dto.getId() == null) {
            throw new NullPointerException("Chưa có id");
        }
        Optional<Product> productOptional = repository.findById(dto.getId());
        Optional<ProductModel> modelOptional = modelRepository.findById(dto.getModelID());
        Optional<Size> sizeOptional = sizeRepository.findById(dto.getSizeID());
//        Validate
        validateUpdate(productOptional,  modelOptional, sizeOptional, dto);

        Product modifiedProduct =  repository.save(
                dto.changeToProduct(
                        sizeOptional.get(), modelOptional.get(), productOptional.get()
                )
        );
        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(modifiedProduct));
    }

    @Override
    public ResponseDto<ProductResponseDto> updateAvatar(ProductRequestUpdateAvatarDto dto) {
        Optional<Product> productOptional = repository.findById(dto.getProductID());
        String urlAvatar = "";
        if (productOptional.isEmpty()) {
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        //      up file image to Amazon s3
        try {
            urlAvatar     = amazonClient.uploadFile(dto.getFileAvatar(), folderName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        Product modifiedProduct =  repository.save(
                dto.changeToProduct( productOptional.get(), urlAvatar)
        );

        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(modifiedProduct));
    }

    @Override
    public ResponseDto<ProductResponseDto> updateStatus(UUID id, boolean status) {
        Optional<Product> productOptional = repository.findById(id);
        if (productOptional.isEmpty()) {
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        productOptional.get().setStatus(status);
        repository.save(productOptional.get());

        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(productOptional.get()));
    }

    @Override
    public ResponseDto<ProductResponseDto> delete(UUID id) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        if(productOptional.get().getModel().getListProduct().size() < 1 ){
            throw new MethodNotAllowedException("hiện tại mẫu Trang Sức chỉ có 1 Trang Sức này nên không thể xóa");
        }
        repository.delete(productOptional.get());

        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(productOptional.get()));
    }

    @Override
    public ResponseDto<ProductResponseDto> getById(UUID id) {
        Optional<Product> productOptional = repository.findById(id);
        if(productOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        return new ResponseDto<ProductResponseDto>(new ProductResponseDto(productOptional.get()));
    }

    @Override
    public ResponseByPageDto<List<ProductResponseDto>> listAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<Product>> listOptional = Optional.ofNullable(repository.findAll(pageable));
        List<ProductResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
//        Product => Convert to ProductResponseDto => Add to listResult
        for (Product product: listOptional.get()) {
            listResult.add(new ProductResponseDto(product));
        }
        return new ResponseByPageDto<>(listOptional.get().getTotalPages(), listResult);
    }


    public void validateInsert(Optional<ProductModel> modelOptional, Optional<Size> sizeOptional, ProductRequestInsertDto dto){
        if(dto.getModelID().equals("")){
            throw new ParamNotValidException("ModelID bị rỗng");
        }
        if(modelOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu Trang Sức để gán Trang Sức vào");
        }
        if(sizeOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy size này");
        }

        //      check this list product already exist in ProductModel?
        for (Product product : modelOptional.get().getListProduct()) {

            if (dto.getSizeID() == product.getSize().getId()){
                throw new RepeatDataException("size này mẫu đã có");
            }
        }

        //      check Sale type  với price sale
        dto.checkPriceSale(modelOptional.get().getPriceRoot());

    }

    public void validateUpdate(Optional<Product> productOptional, Optional<ProductModel> modelOptional, Optional<Size> sizeOptional, ProductRequestUpdateDto dto){

        if(productOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        if(modelOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu Trang Sức để gán Trang Sức vào");
        }
        if(sizeOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy size này");
        }

        //      check this list product already exist in ProductModel?
        if(dto.getSizeID() != productOptional.get().getSize().getId()){
            for (Product product : modelOptional.get().getListProduct()) {
                if (dto.getSizeID() == product.getSize().getId()){
                    throw new RepeatDataException("size này mẫu đã có");
                }
            }
        }
        //      check Sale type  với price sale
        dto.isPriceSale(modelOptional.get().getPriceRoot());
    }

}
