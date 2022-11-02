package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.ProductModel;
import com.rookies.assignment.data.entity.Rating;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IRatingRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.RatingRequestInsertDto;
import com.rookies.assignment.dto.request.RatingRequestUpdateDto;
import com.rookies.assignment.dto.response.RatingResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.exceptions.TooManyRequestsException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    private IRatingRepository repository;
    @Autowired
    private IUserInfoRepository userRepository;
    @Autowired
    private IProductModelRepository modelRepository;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public ResponseDto<RatingResponseDto> insert(RatingRequestInsertDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("Bạn phải đăng nhập trước khi Thanh toán");
        }
        Optional<UserInfo> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        Optional<ProductModel> modelOptional = modelRepository.findById(dto.getModelId());

        if(userOptional.isEmpty() || modelOptional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy User Hoặc Mẫu trang sức");
        }
//      check content
        if(dto.getContent() == null){
            dto.setContent("");
        }
        if(dto.getContent().length() > 200){
            throw new ParamNotValidException("số lượng ký tự dùng đễ đánh giá không được vượt quá 200");
        }

//      check user has already rating
        Optional<Rating> optional = Optional.ofNullable(repository.findByUserAndModel(userOptional.get().getId(), dto.getModelId()));

        if(!optional.isEmpty()){
            throw new TooManyRequestsException("Bạn đã đánh giá cho sản phẩm này rồi");
        }

//      create UserInfoResponseDto to use isOrderThisProductModel()
        UserInfoResponseDto userDto = new UserInfoResponseDto(userOptional.get());
        if(!userDto.isOrderThisProductModel(dto.getModelId())){
            throw new ForbiddenException("Bạn chưa mua mẫu này nên bạn không được đánh giá");
        }
//        => SAVE
        Rating newRating = repository.save(dto.changeToRatingInsert(userOptional.get(), modelOptional.get()));
        return new ResponseDto<>(new RatingResponseDto(newRating));
    }

    @Override
    public ResponseDto<RatingResponseDto> update(RatingRequestUpdateDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdate(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

    @Override
    public ResponseDto<RatingResponseDto> updateStatus(RatingRequestUpdateDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdateStatus(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

    @Override
    public ResponseDto delete(Integer id) {
        Optional<Rating> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        repository.delete(optional.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<RatingResponseDto> getById(Integer id) {
        Optional<Rating> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        return new ResponseDto<>(new RatingResponseDto(optional.get()));
    }

    @Override
    public ResponseDto<List<RatingResponseDto>> listAll() {
        Optional<List<Rating>> listOptional = Optional.ofNullable(repository.findAll());
        List<RatingResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách rỗng");
        }
//        Rating => Convert to RatingResponseDto => Add to listResult
        for (Rating rating:listOptional.get()) {
            listResult.add(new RatingResponseDto(rating));
        }

        return new ResponseDto<>(listResult);
    }
}
