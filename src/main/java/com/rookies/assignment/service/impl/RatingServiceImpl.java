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
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.dto.response.UserInfoResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ParamNotValidException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.exceptions.TooManyRequestsException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IRatingService;
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
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("B???n ph???i ????ng nh???p tr?????c khi ????nh gi??");
        }
        Optional<UserInfo> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        Optional<ProductModel> modelOptional = modelRepository.findById(dto.getModelId());

        if(userOptional.isEmpty() || modelOptional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y User Ho???c M???u trang s???c");
        }
//      check content
        if(dto.getContent() == null){
            dto.setContent("");
        }
        if(dto.getContent().length() > 200){
            throw new ParamNotValidException("s??? l?????ng k?? t??? d??ng ????? ????nh gi?? kh??ng ???????c v?????t qu?? 200");
        }

//      check user has already rating
        Optional<Rating> optional = Optional.ofNullable(repository.findByUserAndModel(userOptional.get().getId(), dto.getModelId()));

        if(!optional.isEmpty()){
            throw new TooManyRequestsException("B???n ???? ????nh gi?? cho s???n ph???m n??y r???i");
        }

//      create UserInfoResponseDto to use isOrderThisProductModel()
        UserInfoResponseDto userDto = new UserInfoResponseDto(userOptional.get());
        if(!userDto.isOrderThisProductModel(dto.getModelId())){
            throw new ForbiddenException("B???n ch??a mua m???u n??y n??n b???n kh??ng ???????c ????nh gi??");
        }
//        => SAVE
        Rating newRating = repository.save(dto.changeToRatingInsert(userOptional.get(), modelOptional.get()));
        return new ResponseDto<>(new RatingResponseDto(newRating));
    }

    @Override
    public ResponseDto<RatingResponseDto> update(RatingRequestUpdateDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdate(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

    @Override
    public ResponseDto<RatingResponseDto> updateStatus(RatingRequestUpdateDto dto) {
        Optional<Rating> optional = repository.findById(dto.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y");
        }
        Rating modifiedRating = repository.save(dto.changeToRatingUpdateStatus(optional.get()));
        return new ResponseDto<>(new RatingResponseDto(modifiedRating));
    }

    @Override
    public ResponseDto delete(Integer id) {
        Optional<Rating> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y");
        }
        repository.delete(optional.get());
        return new ResponseDto<>(null);
    }

    @Override
    public ResponseDto<RatingResponseDto> getById(Integer id) {
        Optional<Rating> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Kh??ng t??m th???y");
        }
        return new ResponseDto<>(new RatingResponseDto(optional.get()));
    }

    @Override
    public ResponseByPageDto<List<RatingResponseDto>> listAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<Rating>> listOptional = Optional.ofNullable(repository.findAll(pageable));
        List<RatingResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh s??ch r???ng");
        }
//        Rating => Convert to RatingResponseDto => Add to listResult
        for (Rating rating:listOptional.get()) {
            listResult.add(new RatingResponseDto(rating));
        }

        return new ResponseByPageDto<>(listOptional.get().getTotalPages(), listResult);
    }
}
