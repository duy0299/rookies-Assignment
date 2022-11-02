package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private IProductRepository productRepository;

    @Override
    public ResponseDto<List<CartDto>> get(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("không đủ quyền");
        }
        List<CartDto> listCart = (List<CartDto>) session.getAttribute("cart"+email);
        if(listCart == null){
            listCart = new ArrayList<>();
        }
        return new ResponseDto<>(listCart);
    }

    @Override
    public ResponseDto<List<CartDto>> addToCartByMethod(CartRequestDto dto, HttpServletRequest request,String method) {
        HttpSession session = request.getSession();
        boolean flag = false;
        int index = 0;

        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        List<CartDto> listCart = (List<CartDto>) session.getAttribute("cart"+email);
        if(listCart == null){
            listCart = new ArrayList<>();
        }

        Optional<Product> productOptional = productRepository.findById(dto.getProductId());
        if (productOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy  trang sức");
        }

        //      check xem xản phẩm này đã có trong giỏ hay chưa
        for (int i=0 ; i< listCart.size(); i++) {
            if (listCart.get(i).getProduct().getId().equals(dto.getProductId())) {
//                đã có trong giỏ hàng
                flag = true;   index = i;   break;
            }
        }

//        added to cart yet
        if (flag) {
//          set quantity By method
            CartDto modifiedCar = listCart.get(index);
            modifiedCar.setQuantity( setQuantityByMethod( listCart.get(index).getQuantity(), dto.getQuantity(), method) );
            modifiedCar.setTotal();
//          check quantity
            if (productOptional.get().getQuantity() < modifiedCar.getQuantity()){
                throw new ParamNotValidException("số lượng vượt quá lượng hàng tồn kho");
            }
            if(modifiedCar.getQuantity() <= 0){
                listCart.remove(index);
            }else{
                listCart.set(index, modifiedCar);
            }
        }else{
            if (productOptional.get().getQuantity() < dto.getQuantity()){
                throw new ParamNotValidException("số lượng vượt quá lượng hàng tồn kho");
            }
            listCart.add(new CartDto(productOptional.get(), dto.getQuantity()));
        }

//      =>SAVE
        session.setAttribute("cart"+email, listCart);
        return new ResponseDto<>(listCart);
    }

    private Integer setQuantityByMethod(Integer oldQuantity, Integer newQuantity, String method){
        switch (method){
            case "up":{
                return (oldQuantity + 1);
            }
            case "down":{
                return (oldQuantity - 1);
            }
            case "change":{
                return (newQuantity);
            }
            default:{
                return (oldQuantity + newQuantity);
            }
        }
    }
}
