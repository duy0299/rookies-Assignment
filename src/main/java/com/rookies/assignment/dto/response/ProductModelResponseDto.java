package com.rookies.assignment.dto.response;

import com.rookies.assignment.dto.flat.ModelImageDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import com.rookies.assignment.data.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class ProductModelResponseDto extends ProductModelDtoFlat {
    private List<ProductResponseDto> listProduct;
    private List<ModelImageDtoFlat> listImages;
    private List<RatingResponseDto> listRatings;
    private List<CategoriesResponseDto> listCategories;
    private List<WishlistDto> listWishlists;

    public ProductModelResponseDto(ProductModel model){
        super(model);
        listProduct = setListProduct(model.getListProduct());
        listImages  = setListImages(model.getListImages());
        listRatings = setListRatings(model.getListRatings());
        listCategories = setListCategories(model.getListCategories());
        listWishlists = setWishlists(model.getListWishlists());
    }

    private List<ProductResponseDto> setListProduct(List<Product> list){
        List<ProductResponseDto> result = new ArrayList<>();
        for(Product product : list){
            result.add(new ProductResponseDto(product));
        }
        return result;
    }

    private List<ModelImageDtoFlat> setListImages(List<ModelImage> list){
        List<ModelImageDtoFlat> result = new ArrayList<>();
        for(ModelImage image : list){
            result.add(new ModelImageDtoFlat(image));
        }
        return result;
    }

    private List<RatingResponseDto> setListRatings(List<Rating> list){
        List<RatingResponseDto> result = new ArrayList<>();
        for(Rating rating : list){
            result.add(new RatingResponseDto(rating));
        }
        return result;
    }

    private List<CategoriesResponseDto> setListCategories(List<Categories> list){

        List<CategoriesResponseDto> result = new ArrayList<>();
        for(Categories categories : list){
            result.add(new CategoriesResponseDto(categories));
        }
        return result;
    }

    private List<WishlistDto> setWishlists(List<Wishlist> list){
        List<WishlistDto> result = new ArrayList<>();
        for(Wishlist wishlist : list){
            result.add(new WishlistDto(wishlist));
        }
        return result;
    }
}