package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Order;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IOrderItemRepository;
import com.rookies.assignment.data.repository.IOrderRepository;
import com.rookies.assignment.data.repository.IProductRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.CartRequestDto;
import com.rookies.assignment.dto.request.OrderRequestInsertDto;
import com.rookies.assignment.dto.request.OrderRequestUpdateDto;
import com.rookies.assignment.dto.response.CartDto;
import com.rookies.assignment.dto.response.OrderResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderRepository repository;
    @Autowired
    private IUserInfoRepository userRepository;
    @Autowired
    private IOrderItemRepository itemRepository;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public ResponseDto<OrderResponseDto> insert(OrderRequestInsertDto dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        List<CartDto> listCart = new ArrayList<>();
        if(email==null){
            throw new ForbiddenException("Bạn phải đăng nhập trước khi Thanh toán");
        }
        Optional<UserInfo> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        for (CartRequestDto cartRequest: dto.getListProduct()) {
            Optional<Product> productOptional = productRepository.findById(cartRequest.getProductId());
            if (productOptional.isEmpty()){
                throw new ResourceFoundException("Không tìm thấy  trang sức");
            }
            if(cartRequest.getQuantity() < 1 || cartRequest.getQuantity() >productOptional.get().getQuantity() ){
                throw new ParamNotValidException("Số lượng  " +productOptional.get().getName() + "vượt quá lượng tồn kho hoặc thấp hơn 1");
            }
            listCart.add(new CartDto(productOptional.get(), cartRequest.getQuantity()));
        }

//       validate
        validateInsert(optionalUser, listCart);
//       create Order
        Order newOrder = repository.save(dto.changeToOrderInsert(optionalUser.get(), listCart));
        return new ResponseDto<>(new OrderResponseDto(newOrder));
    }

    @Override
    public ResponseDto<OrderResponseDto> updateStatus(OrderRequestUpdateDto dto) {
        Optional<Order> optionalOrder = repository.findById(dto.getOrder_id());
        if (optionalOrder.isEmpty()){
            throw new ResourceFoundException("Đơn hàng này không tồn tại");
        }
        Order modifiedOrder = repository.save(dto.changeToOrderUpdateStatus(optionalOrder.get()));
        return new ResponseDto<>(new OrderResponseDto(modifiedOrder));
    }

    @Override
    public ResponseDto<OrderResponseDto> delete(UUID id) {
        Optional<Order> optionalOrder = repository.findById(id);
        if (optionalOrder.isEmpty()){
            throw new ResourceFoundException("Đơn hàng này không tồn tại");
        }
        repository.delete(optionalOrder.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<OrderResponseDto> getById(UUID id) {
        Optional<Order> optionalOrder = repository.findById(id);
        if (optionalOrder.isEmpty()){
            throw new ResourceFoundException("Đơn hàng này không tồn tại");
        }
        return new ResponseDto<>(new OrderResponseDto(optionalOrder.get()));
    }

    @Override
    public ResponseByPageDto<List<OrderResponseDto>> listAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<Order>> optional = Optional.ofNullable(repository.findAll(pageable));
        List<OrderResponseDto> listResult = new ArrayList<>();
        if (optional.isEmpty()){
            throw new ResourceFoundException("Đơn hàng này không tồn tại");
        }
//        Order => Convert to OrderResponseDto => Add to listResult
        for (Order order: optional.get()) {
            listResult.add(new OrderResponseDto(order));
        }
        return new ResponseByPageDto<>(optional.get().getTotalPages(), listResult);
    }

    public void validateInsert(Optional<UserInfo> optionalUser, List<CartDto> listCart){
        if(optionalUser.isEmpty()){
            throw new ResourceFoundException("User không tồn tại");
        }
        if(listCart == null){
            throw new ResourceFoundException("Giỏ hàng của bạn đang rỗng. vui lòng thêm sản phẩm vào giỏ.");
        }
        if(listCart.size() == 0){
            throw new ResourceFoundException("Giỏ hàng của bạn đang rỗng. vui lòng thêm sản phẩm vào giỏ.");
        }
        for (CartDto cartDto:listCart) {
            if(!cartDto.isValidQuantity()){
                throw new ParamNotValidException("trong giỏ hàng đang có một sản phẩm bị lỗi số lượng ít hơn 0 hoặc nhiều hơn trong kho");
            }
        }

    }
}
