package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.dto.flat.ModelImageDtoFlat;
import com.rookies.assignment.dto.flat.ProductModelDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class ProductModelResponseDto extends ProductModelDtoFlat {
    private List<ProductResponseDto> listProduct;
    private List<ModelImageDtoFlat> listImages;
    private List<RatingResponseDto> listRatings;
    private List<CategoriesResponseDto> listCategories;
    private List<WishlistResponseDto> listWishlists;
    private BigDecimal priceTo, priceFrom;

    public ProductModelResponseDto(ProductModel model){
        super(model);
        listProduct = setListProduct(model.getListProduct());
        listImages  = setListImages(model.getListImages());
        listRatings = setListRatings(model.getListRatings());
        listCategories = setListCategories(model.getListCategories());
        listWishlists = setWishlists(model.getListWishlists());
        priceTo  = setPriceTo(listProduct);
        priceFrom = setPriceFrom(listProduct);
    }

    public BigDecimal setPriceFrom(List<ProductResponseDto> list){
        BigDecimal min = new BigDecimal(0);
        for (ProductResponseDto dto: list) {
            if (dto.getCurrentPrice().compareTo(min) == -1){
                min = dto.getCurrentPrice();
            }
        }
        return min;
    }

    public BigDecimal setPriceTo(List<ProductResponseDto> list){
        BigDecimal max = new BigDecimal(0);
        for (ProductResponseDto dto: list) {
            if (dto.getCurrentPrice().compareTo(max) == 1){
                max = dto.getCurrentPrice();
            }
        }
        return max;
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

    private List<WishlistResponseDto> setWishlists(List<Wishlist> list){
        List<WishlistResponseDto> result = new ArrayList<>();
        for(Wishlist wishlist : list){
            result.add(new WishlistResponseDto(wishlist));
        }
        return result;
    }
}