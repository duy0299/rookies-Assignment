package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.*;
import com.rookies.assignment.data.repository.IProductModelRepository;
import com.rookies.assignment.data.repository.IRatingRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.RatingRequestDto;
import com.rookies.assignment.dto.response.RatingResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.exceptions.TooManyRequestsException;
import com.rookies.assignment.service.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseDto<RatingResponseDto> insert(RatingRequestDto dto) {
        Optional<UserInfo> userOptional = userRepository.findById(dto.getUser_id());
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
        Optional<Rating> optional = Optional.ofNullable(repository.findByUserAndMode(dto.getUser_id(), dto.getModelId()));
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
    public ResponseDto<RatingResponseDto> update(RatingRequestDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdate(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

    @Override
    public ResponseDto<RatingResponseDto> updateStatus(RatingRequestDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdateStatus(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

//    @Override
//    public ResponseDto delete(Integer id) {
//        Optional<Rating> optional = repository.findById(id);
//        if(optional.isEmpty()){
//            throw new ResourceFoundException("Không tìm thấy");
//        }
//        return new ResponseDto<>(null);
//    }

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
