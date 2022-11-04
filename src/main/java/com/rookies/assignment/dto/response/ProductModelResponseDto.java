package com.rookies.assignment.dto.response;


import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.dto.flat.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
public class ProductModelResponseDto extends ProductModelDtoFlat {
    private List<ProductForModelDto> listProduct;
    private List<String> listImages;
    private List<RatingOfModel> listRating;
    private CategoriesDtoFlat categories;
    private List<WishlistOfModel> listWishlist;
    private float start;
    private BigDecimal priceTo, priceFrom;

    public ProductModelResponseDto(ProductModel model){
        super(model);
        listProduct = setListProduct(model.getListProduct());
        listImages  = setListImages(model.getListImages());
        listRating      = setRatings(model.getListRatings());
        listWishlist = setListWishlist(model.getListWishlists());
        categories  = new CategoriesDtoFlat(model.getCategories());
        priceTo     = setPriceTo(listProduct, model.getPriceRoot());
        priceFrom   = setPriceFrom(listProduct, model.getPriceRoot());
        start = setStart(model.getListRatings());
    }


    public BigDecimal setPriceFrom(List<ProductForModelDto> list, BigDecimal priceRoot){

        BigDecimal min = new BigDecimal(String.valueOf(priceRoot));
        for (ProductForModelDto dto: list) {
            if (dto.getCurrentPrice().compareTo(min) == -1){
                min = dto.getCurrentPrice();
            }
        }
        return min;
    }
    public BigDecimal setPriceTo(List<ProductForModelDto> list, BigDecimal priceRoot){
        BigDecimal max = new BigDecimal(String.valueOf(priceRoot));
        for (ProductForModelDto dto: list) {
            if (dto.getCurrentPrice().compareTo(max) == 1){
                max = dto.getCurrentPrice();
            }
        }
        return max;
    }
    private List<ProductForModelDto> setListProduct(List<Product> list){
        List<ProductForModelDto> result = new ArrayList<>();
        if(list == null){
            return result;
        }
        for(Product product : list){
            result.add(new ProductForModelDto(product));
        }
        return result;
    }
    private List<String> setListImages(List<ModelImage> list){
        List<String> result = new ArrayList<>();
        if(list == null){
            return result;
        }
        for(ModelImage image : list){
            result.add(image.getUrlImage());
        }
        return result;
    }
    private List<RatingOfModel> setRatings(List<Rating> list){
        List<RatingOfModel> result = new ArrayList<>();
        if(list == null){
            return result;
        }
        for(Rating rating : list){
            result.add(new RatingOfModel(rating));
        }
        return result;
    }
    private List<WishlistOfModel> setListWishlist(List<Wishlist> list){
        List<WishlistOfModel> result = new ArrayList<>();
        if(list == null){
            return result;
        }
        for(Wishlist wishlist : list){
            result.add(new WishlistOfModel(wishlist));
        }
        return result;
    }
    private float setStart(List<Rating> list){
        float sum = 0;
        if(list == null){
            return 0;
        }
        for(Rating rating : list){
            sum += rating.getRating();
        }
        return Math.round(sum/list.size());
    }


    @Data
    private class ProductForModelDto extends ProductDtoFlat {

        private SizeDtoFlat size;

        public ProductForModelDto(Product product) {
            super(product);
            size = new SizeDtoFlat(product.getSize());
        }

        public  Product changeToProduct(){
            Product product = new Product();

            product.setId(getId());
            product.setSize(size.changeToSize());
            product.setName(getName());
            product.setAvatar(getAvatar());
            product.setSaleType(getSaleType());
            product.setPriceSale(getPriceSale());
            product.setQuantity(getQuantity());
            product.setSoldProductQuantity(getSoldProductQuantity());
            product.setStatus(isStatus());
            product.setTimeCreate(getTimeCreate());
            product.setTimeUpdate(getTimeUpdate());

            return product;
        }




    }
    @Data
    private class RatingOfModel extends RatingDtoFlat {
        private userRating user;
        public RatingOfModel(Rating rating) {
            super(rating);
            user = new userRating(rating.getUserInfo());
        }

    }
    @Data
    private class userRating {
        private  String firstName;
        private  String lastName;
        private  String phoneNumber;
        private  String gender;
        private  String email;
        private  String avatar;

        public userRating(UserInfo userInfo) {
            this.firstName = userInfo.getFirstName();
            this.lastName = userInfo.getLastName();
            this.phoneNumber = userInfo.getPhoneNumber();
            this.email = userInfo.getEmail();
            this.avatar = userInfo.getAvatar();
            if(userInfo.isGender()){
                this.gender = "Nam";
            }else{
                this.gender = "Nữ";
            }
        }



    }

    @Data
    @NoArgsConstructor
    private class WishlistOfModel  {
        private  String email;
        private  int id;
        private  boolean status;

        public WishlistOfModel(Wishlist wishlist){
            email = wishlist.getUserInfo().getEmail();
            id = wishlist.getId();
            status = wishlist.isStatus();
        }
    }
}