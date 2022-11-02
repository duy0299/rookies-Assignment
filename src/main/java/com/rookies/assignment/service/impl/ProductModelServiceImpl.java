package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.data.repository.ICategoriesRepository;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.request.*;
import com.rookies.assignment.dto.request.productmodel.ModelAndProductRequestInsertDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestUpdateDto;
import com.rookies.assignment.dto.request.productmodel.ModelRequestUpdateImageDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.IProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductModelServiceImpl implements IProductModelService {
    @Autowired
    private IProductModelRepository repository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISizeRepository sizeRepository;
    @Autowired
    private ICategoriesRepository categoriesRepository;
    @Autowired
    private AmazonClient amazonClient;
    @Value("${amazonProperties.folderSaveProduct}")
    private String folderName;




    @Override
    public ResponseDto<ProductModelResponseDto> insert(ModelRequestInsertDto dto) {
        Optional<Categories> optionalCategories = categoriesRepository.findById(dto.getCategoriesID());
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Loại trang sức để gán vào Mẫu ");
        }
        List<ModelImage> listModelImage = setListImage(dto.getListImages());

        ProductModel newModel =  repository.save(dto.changeToProductModel(optionalCategories.get(), listModelImage));
        return new ResponseDto<>(new ProductModelResponseDto(newModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> insertModelAndProduct(ModelAndProductRequestInsertDto dto) {
        Optional<Categories> optionalCategories = categoriesRepository.findById(dto.getCategoriesID());
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Loại trang sức để gán vào Mẫu ");
        }
        List<Product> listProduct = setListProduct(dto);
        List<ModelImage> listModelImage = setListImage(dto.getListImages());

        ProductModel newModel =  repository.save(dto.changeToProductModel(optionalCategories.get(), listProduct, listModelImage));
        return new ResponseDto<>(new ProductModelResponseDto(newModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> update(ModelRequestUpdateDto dto) {
        Optional<ProductModel> ProductOptional = repository.findById(dto.getId());
        Optional<Categories> optionalCategories = categoriesRepository.findById(dto.getCategoriesID());
        if(ProductOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu trang sức");
        }
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Loại trang sức để gán vào Mẫu ");
        }

        ProductModel modifiedModel = repository.save(dto.changeToProductModel(optionalCategories.get(), ProductOptional.get()));
        return new ResponseDto<>(new ProductModelResponseDto(modifiedModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> delete(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu trang sức");
        }

        repository.delete(optional.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<ProductModelResponseDto> updateImage(ModelRequestUpdateImageDto dto) {
        Optional<ProductModel> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu trang sức");
        }
        List<ModelImage> listImage = setListImage(dto.getListImages());
        ProductModel modifiedModel = repository.save(dto.changeToProductModel(listImage, optional.get()));
        return new ResponseDto<>(new ProductModelResponseDto(modifiedModel));
    }

//    change satatus of this  product model  to false:  hide the product, don't sell anymore.
    @Override
    public ResponseDto<ProductModelResponseDto> updateStatus(UUID id, boolean status) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        ProductModel model = optional.get();
        model.setStatus(status);

        repository.save(model);
        return new ResponseDto<>(new ProductModelResponseDto(model));
    }

//    get a model product by ID
    @Override
    public ResponseDto<ProductModelResponseDto> getById(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        return new ResponseDto<>(new ProductModelResponseDto(optional.get()));
    }

    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByPage(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<ProductModel>> listOptional = Optional.ofNullable(repository.findByStatus(true, pageable));
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        System.out.println( listOptional);

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        System.out.println(listOptional.get().getTotalPages());
        for (ProductModel model: listOptional.get().toList()) {
            listResult.add(new ProductModelResponseDto(model));
        }

        return new ResponseByPageDto(listOptional.get().getTotalPages(), listResult);
    }

    //    List all model product
    @Override
    public ResponseDto<List<ProductModelResponseDto>> listAll() {
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
//        ProductModel =>convert to ProductModelResponseDto => add to listResult
        for (ProductModel model: listOptional.get()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return new ResponseDto<>(listResult);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listMostPopularProduct() {
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();
        List<ProductModelResponseDto> dataResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        //        ProductModel =>convert to ProductModelResponseDto => add to listResult
        for (ProductModel model: listOptional.get()) {
            listResult.add(new ProductModelResponseDto(model));
        }

        listResult.sort( (ProductModelResponseDto p1, ProductModelResponseDto p2)->{
            if(p1.getListUserLike().size() < p2.getListUserLike().size()) {
                return 1;
            }else  if(p1.getListUserLike().size() > p2.getListUserLike().size()) {
                return -1;
            }
            return 0;
        });
        if(listResult.size()>10){
            for (int i = 0; i < 10; i++) {
                dataResult.add(listResult.get(i));
            }
            return new ResponseDto<>(dataResult);
        }
        return new ResponseDto<>(listResult);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listNewProduct() {
        Long oneMonth = 60*60*24*30*1L;
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        Date now = new Date();
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        //        ProductModel =>convert to ProductModelResponseDto => add to listResult
        for (ProductModel model: listOptional.get()) {
            Date timeCreate = new Date(model.getTimeCreate().getTime());
            System.out.println(now.getTime() + " - " +timeCreate.getTime() + " = " + (now.getTime()-timeCreate.getTime())/1000 + " >< " +oneMonth);
            if((now.getTime()-timeCreate.getTime())/1000 < oneMonth ){
                listResult.add(new ProductModelResponseDto(model));
            }
        }
        return new ResponseDto<>(listResult);
    }

//    Search product model by name
    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByName(String name, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        name = changeToText(name);

        Optional<Page<ProductModel>> listOptional = Optional.ofNullable(repository.findByNameAndStatus(name, true, pageable));
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        for (ProductModel model: listOptional.get().toList()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return new ResponseByPageDto(listOptional.get().getTotalPages(), listResult);
    }

    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }

//        for (ProductModel model: listOptional.get().toList()) {
//            listResult.add(new ProductModelResponseDto(model));
//        }

        return new ResponseByPageDto(2, listResult);
    }

    //Change accented characters to unsigned
    public String changeToText(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("á|à|ả|ạ|ã|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ", "a");
        text = text.replaceAll("é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ", "e");
        text = text.replaceAll("i|í|ì|ỉ|ĩ|ị", "i");
        text = text.replaceAll("ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ", "o");
        text = text.replaceAll("ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự", "u");
        text = text.replaceAll("ý|ỳ|ỷ|ỹ|ỵ", "y");
        text = text.replaceAll("đ", "d");
        return text;
    }


    private List<Product> setListProduct(ModelAndProductRequestInsertDto dto){
        List<Product> listResult =  new ArrayList<>();

        for (int i=0; i< dto.getListProduct().size()-1; i++) {
            for (int a=i+1; a< dto.getListProduct().size(); a++) {
                if (dto.getListProduct().get(i).getSizeID() == dto.getListProduct().get(a).getSizeID()) {
                    throw new RepeatDataException("có sản phẩm bị trùng size");
                }
            }
        }

        for (ProductRequestInsertDto insertDto : dto.getListProduct()) {
            String urlAvatar = "";
            insertDto.checkPriceSale(dto.getPriceRoot());

            Optional<Size> sizeOptional = sizeRepository.findById(insertDto.getSizeID());

            if (sizeOptional.isEmpty()){
                throw new ResourceFoundException("Không tìm thấy size này");
            }
            Size size = sizeOptional.get();
            //      up file image to Amazon s3
            try {
                urlAvatar     = amazonClient.uploadFile(insertDto.getFileAvatar(), folderName);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            Product product =  insertDto.changeProductToInsertModel(urlAvatar);
            product.setSize(size);
            listResult.add(product);
        }
        return  listResult;
    }
    private List<ModelImage> setListImage(List<MultipartFile> listFile){
        List<ModelImage> listResult =  new ArrayList<>();

        for (MultipartFile file : listFile) {
            ModelImage modelImage = new ModelImage();
            String urlAvatar = "";
            //      up file image to Amazon s3
            try {
                urlAvatar     = amazonClient.uploadFile(file, folderName);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            modelImage.setUrlImage(urlAvatar);
            listResult.add(modelImage);
        }
        return  listResult;
    }

}
