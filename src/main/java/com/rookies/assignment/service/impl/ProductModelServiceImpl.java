package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.data.repository.*;
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
import com.rookies.assignment.security.jwt.JwtProvider;
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
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IUserInfoRepository userRepository;



    @Override
    public ResponseDto<ProductModelResponseDto> insert(ModelRequestInsertDto dto) {
        Optional<Categories> optionalCategories = categoriesRepository.findById(dto.getCategoriesID());
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y Lo???i trang s???c ????? g??n v??o M???u ");
        }
        List<ModelImage> listModelImage = setListImage(dto.getListImages());

        ProductModel newModel =  repository.save(dto.changeToProductModel(optionalCategories.get(), listModelImage));
        return new ResponseDto<>(new ProductModelResponseDto(newModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> insertModelAndProduct(ModelAndProductRequestInsertDto dto) {
        Optional<Categories> optionalCategories = categoriesRepository.findById(dto.getCategoriesID());
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y Lo???i trang s???c ????? g??n v??o M???u ");
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
            throw new ResourceFoundException("Kh??ng t??m th???y M???u trang s???c");
        }
        if(optionalCategories.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y Lo???i trang s???c ????? g??n v??o M???u ");
        }

        ProductModel modifiedModel = repository.save(dto.changeToProductModel(optionalCategories.get(), ProductOptional.get()));
        return new ResponseDto<>(new ProductModelResponseDto(modifiedModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> delete(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y M???u trang s???c");
        }

        repository.delete(optional.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<ProductModelResponseDto> updateImage(ModelRequestUpdateImageDto dto) {
        Optional<ProductModel> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y M???u trang s???c");
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
            throw new ResourceFoundException("Kh??ng t??m th???y Trang S???c");
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
            throw new ResourceFoundException("Kh??ng t??m th???y Trang S???c");
        }
        return new ResponseDto<>(new ProductModelResponseDto(optional.get()));
    }

    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByPage(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<ProductModel>> listOptional = Optional.ofNullable(repository.findAll(pageable));
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        System.out.println( listOptional);

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }
        System.out.println(listOptional.get().getTotalPages());
        for (ProductModel model: listOptional.get().toList()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return  new ResponseByPageDto(listOptional.get().getTotalPages(), listResult);
    }

    //    List all model product
    @Override
    public ResponseDto<List<ProductModelResponseDto>> listAll() {
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }

        for (ProductModel model: listOptional.get()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return  new ResponseDto(listResult);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listMostPopularProduct() {
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();
        List<ProductModelResponseDto> dataResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }
        //        ProductModel =>convert to ProductModelResponseDto => add to listResult
        for (ProductModel model: listOptional.get()) {
            listResult.add(new ProductModelResponseDto(model));
        }

        listResult.sort( (ProductModelResponseDto p1, ProductModelResponseDto p2)->{
            if(p1.getListWishlist().size() < p2.getListWishlist().size()) {
                return 1;
            }else  if(p1.getListWishlist().size() > p2.getListWishlist().size()) {
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
            throw new ResourceFoundException("Danh s??ch r???ng");
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
        Optional<List<ProductModel>> listOptional = repository.findByStatusTrue();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y");
        }
        List<ProductModel> list = new ArrayList<>();
//        compare names

        for (ProductModel model :listOptional.get()) {
            if(changeToText(model.getName()).contains(changeToText(name))){
                list.add(model);
            }
        }
        return pagination(list, page+1,  size);
    }

    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByCategories(int categoriesID, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<List<Categories>> categoriesOptional = Optional.ofNullable(categoriesRepository.findByIdOrParentCategoriesId(categoriesID, categoriesID));
        if(categoriesOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }
        List<Integer> listCategoriesId = new ArrayList<>();
        for (Categories categories : categoriesOptional.get()) {
            listCategoriesId.add(categories.getId());
        }
        Optional<Page<ProductModel>> ModelOptional = Optional.ofNullable(repository.findByCategoriesIdInAndStatusTrue(listCategoriesId,  pageable));

        List<ProductModelResponseDto> listResult = new ArrayList<>();

        for (ProductModel model: ModelOptional.get().toList()) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return new ResponseByPageDto(ModelOptional.get().getTotalPages(), listResult);
    }

    @Override
    public ResponseByPageDto <List <ProductModelResponseDto> > listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<List<ProductModel>> listOptional = Optional.ofNullable(repository.findAll());
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }

//        for (ProductModel model: listOptional.get().toList()) {
//            listResult.add(new ProductModelResponseDto(model));
//        }

        return new ResponseByPageDto(2, listResult);
    }

    //Change accented characters to unsigned
    public String changeToText(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("??|??|???|???|??|??|???|???|???|???|???|??|???|???|???|???|???", "a");
        text = text.replaceAll("??|??|???|???|???|??|???|???|???|???|???", "e");
        text = text.replaceAll("i|??|??|???|??|???", "i");
        text = text.replaceAll("??|??|???|??|???|??|???|???|???|???|???|??|???|???|???|???|???", "o");
        text = text.replaceAll("??|??|???|??|???|??|???|???|???|???|???", "u");
        text = text.replaceAll("??|???|???|???|???", "y");
        text = text.replaceAll("??", "d");
        return text.toLowerCase();
    }


    private List<Product> setListProduct(ModelAndProductRequestInsertDto dto){
        List<Product> listResult =  new ArrayList<>();

        for (int i=0; i< dto.getListProduct().size()-1; i++) {
            for (int a=i+1; a< dto.getListProduct().size(); a++) {
                if (dto.getListProduct().get(i).getSizeID() == dto.getListProduct().get(a).getSizeID()) {
                    throw new RepeatDataException("c?? s???n ph???m b??? tr??ng size");
                }
            }
        }

        for (ProductRequestInsertDto insertDto : dto.getListProduct()) {
            String urlAvatar = "";
            insertDto.checkPriceSale(dto.getPriceRoot());

            Optional<Size> sizeOptional = sizeRepository.findById(insertDto.getSizeID());

            if (sizeOptional.isEmpty()){
                throw new ResourceFoundException("Kh??ng t??m th???y size n??y");
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

    public ResponseByPageDto<List <ProductModelResponseDto> >pagination(List<ProductModel> list, int page, int size){
        int start = (page==1)?0:(page - 1) * size;
        int end   = page * size;
        int totalPage = 0;
        List<ProductModel> result = new ArrayList<>();
        if(list.size()>1 && list.size()>=end){
            for (int i = start; i < end; i++) {
                result.add(list.get(i));
            }
        }else{
            for (int i = start; i < list.size(); i++) {
                result.add(list.get(i));
            }
        }

        if(list.size() > 0){
            if(list.size() % size == 0){
                totalPage=  list.size() / size;
            }else{
                totalPage = (list.size() / size) + 1;
            }
        }
        List<ProductModelResponseDto> listResult = new ArrayList<>();

        for (ProductModel model: result) {
            listResult.add(new ProductModelResponseDto(model));
        }
        return new ResponseByPageDto(totalPage, listResult);
    }
}
