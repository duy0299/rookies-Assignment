package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.entity.Wishlist;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.data.repository.IWishlistRepository;
import com.rookies.assignment.dto.request.WishlistRequestInsertDto;
import com.rookies.assignment.dto.request.WishlistRequestUpdateDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.WishlistResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.RepeatDataException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements IWishlistService {

    @Autowired
    private IWishlistRepository repository;
    @Autowired
    private IUserInfoRepository userRepository;
    @Autowired
    private IProductModelRepository modelRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ResponseDto<WishlistResponseDto> insert(WishlistRequestInsertDto dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("Bạn phải đăng nhập trước khi thêm vào danh sách yêu thích");
        }

        Optional<UserInfo> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        Optional<ProductModel> optionalModel = modelRepository.findById(dto.getModelId());

        Optional<Wishlist> optionalWishlist = Optional.ofNullable(repository.findByUserAndMode(optionalUser.get().getId(), dto.getModelId()));

        if(optionalUser.isEmpty() || optionalModel.isEmpty()){
            throw new ResourceFoundException("User hoặc Trang sức không tồn tại");
        }
//
        if(!optionalWishlist.isEmpty()){
            throw new RepeatDataException("Đã thêm vào danh sách yêu thích rồi");
        }

//        => SAVE
        Wishlist newWishlist = repository.save(dto.changeToWishlistInsert(optionalUser.get(), optionalModel.get()));
        return new ResponseDto<>(new WishlistResponseDto(newWishlist));
    }

    @Override
    public ResponseDto<WishlistResponseDto> updateStatus(WishlistRequestUpdateDto dto) {
        Optional<Wishlist> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        Wishlist modifiedWishlist = repository.save(dto.changeToWishlistUpdateStatus(optional.get()));
        return new ResponseDto<>(new WishlistResponseDto(modifiedWishlist));
    }

    @Override
    public ResponseDto delete(Integer id) {
        Optional<Wishlist> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        repository.delete(optional.get());
        return new ResponseDto(null);
    }

    @Override
    public ResponseDto<WishlistResponseDto> getById(Integer id) {
        Optional<Wishlist> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        return new ResponseDto<WishlistResponseDto>(new WishlistResponseDto(optional.get()));
    }

    @Override
    public ResponseDto<List<WishlistResponseDto>> listAll() {
        Optional<List<Wishlist>> listOptional = Optional.ofNullable(repository.findAll());
        List<WishlistResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
        for (Wishlist wishlist:listOptional.get()) {
            listResult.add(new WishlistResponseDto(wishlist));
        }

        return new ResponseDto<List<WishlistResponseDto>>(listResult);
    }


    @Override
    public ResponseDto<List<WishlistResponseDto>> listByUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        List<WishlistResponseDto> listResult = new ArrayList<>();
        if(email==null){
            throw new ForbiddenException("Bạn chưa đăng nhập");
        }

        Optional<UserInfo> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));
        if(optionalUser.isEmpty()){

        }
        for (Wishlist wishlist:optionalUser.get().getListWishlists()) {
            listResult.add(new WishlistResponseDto(wishlist));
        }

        return new ResponseDto<>(listResult);
    }
}
