package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.data.repository.ICategoriesRepository;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.data.repository.ISizeRepository;
import com.rookies.assignment.dto.request.ModelRequestInsertDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateDto;
import com.rookies.assignment.dto.request.ModelRequestUpdateImageDto;
import com.rookies.assignment.dto.request.ProductRequestInsertDto;
import com.rookies.assignment.dto.response.ProductModelResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.AmazonClient;
import com.rookies.assignment.service.IProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

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
        List<Categories> listCategories = setListCategories(dto.getListCategoriesID());
        List<Product> listProduct = setListProduct(dto);
        List<ModelImage> listModelImage = setListImage(dto.getListImages());

        ProductModel newModel = repository.save(dto.changeToProductModel(listCategories, listProduct, listModelImage));
        return new ResponseDto<>(new ProductModelResponseDto(newModel));
    }

    @Override
    public ResponseDto<ProductModelResponseDto> update(ModelRequestUpdateDto dto) {
        Optional<ProductModel> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Mẫu trang sức");
        }
        List<Categories> listCategories = setListCategories(dto.getListCategoriesID());
        ProductModel modifiedModel = repository.save(dto.changeToProductModel(listCategories, optional.get()));
        return new ResponseDto<>(new ProductModelResponseDto(modifiedModel));
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
    public ResponseDto<ProductModelResponseDto> updateStatus(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        ProductModel model = optional.get();
        if (model.isStatus()) {
            model.setStatus(false);
        }else{
            model.setStatus(true);
        }
        repository.save(model);
        return new ResponseDto<ProductModelResponseDto>(new ProductModelResponseDto(model));
    }

//    get a model product by ID
    @Override
    public ResponseDto<ProductModelResponseDto> getById(UUID id) {
        Optional<ProductModel> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy Trang Sức");
        }
        return new ResponseDto<ProductModelResponseDto>(new ProductModelResponseDto(optional.get()));
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
        return new ResponseDto<List<ProductModelResponseDto>>(listResult);
    }

//    Search product model by name
    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByName(String name) {
        name = changeToText(name);
        ResponseDto<List<ProductModelResponseDto>> all = listAll();
        List<ProductModelResponseDto> result = new ArrayList<ProductModelResponseDto>();
        String check = "^.*"+name+".*$";
        for(ProductModelResponseDto p : all.getResult()) {
            if(Pattern.matches(check, changeToText(p.getName()))) {
                result.add(p);
            }
        }
        return new ResponseDto<List<ProductModelResponseDto>>(result);
    }

    @Override
    public ResponseDto<List<ProductModelResponseDto>> listByPriceRange(BigDecimal priceTo, BigDecimal priceFrom) {
        ResponseDto<List<ProductModelResponseDto>> all = listAll();
        List<ProductModelResponseDto> result = new ArrayList<ProductModelResponseDto>();
        for (ProductModelResponseDto dto: all.getResult()) {
            if(dto.getPriceTo().compareTo(priceTo) != 1  && dto.getPriceFrom().compareTo(priceFrom)!=-1){
                result.add(dto);
            }
        }
        return new ResponseDto<List<ProductModelResponseDto>>(result);
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

    private List<Categories> setListCategories(List<Integer> listCategoriesID){
        List<Categories> listResult =  new ArrayList<>();
        for (Integer categoriesID : listCategoriesID) {
            Optional<Categories> optionalCategories = categoriesRepository.findById(categoriesID);
            if(optionalCategories.isEmpty()){
                throw new ResourceFoundException("Không tìm thấy Loại trang sức để gán vào Mẫu ");
            }
            listResult.add(optionalCategories.get());
        }
        return listResult;
    }
    private List<Product> setListProduct(ModelRequestInsertDto dto){
        List<Product> listResult =  new ArrayList<>();
        for (int i=0; i< dto.getListProduct().size(); i++) {
            for (int a=0; a< dto.getListProduct().size(); a++) {
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
            //      up file image to Amazon s3
            try {
                urlAvatar     = amazonClient.uploadFile(insertDto.getFileAvatar(), folderName);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
            listResult.add(insertDto.changeProductToInsertModel(sizeOptional.get(),urlAvatar));
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
