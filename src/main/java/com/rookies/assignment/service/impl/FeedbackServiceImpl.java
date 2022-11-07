package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IFeedbackRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.FeedbackRequestInsertDto;
import com.rookies.assignment.dto.response.FeedbackResponseDto;
import com.rookies.assignment.dto.response.ResponseByPageDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ForbiddenException;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.exceptions.TooManyRequestsException;
import com.rookies.assignment.security.jwt.JwtProvider;
import com.rookies.assignment.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private IFeedbackRepository repository;
    @Autowired
    private IUserInfoRepository userRepository;
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public ResponseDto<FeedbackResponseDto> insert(FeedbackRequestInsertDto dto, HttpServletRequest request) {
        String email = jwtProvider.getUserIFromHttpServletRequest(request);
        if(email==null){
            throw new ForbiddenException("Bạn phải đăng nhập trước khi Đánh giá");
        }

        Optional<UserInfo> optionalUserInfo = Optional.ofNullable(userRepository.findByEmail(email));

//        check User not Empty
        if(optionalUserInfo.isEmpty()){
            throw new ResourceFoundException("User này không tồn tại");
        }

//        check if the user has submitted feedback today?
        if(!dto.isFirstFeedbackToday(optionalUserInfo.get().getListFeedbacks())){
            throw new TooManyRequestsException("một ngày mỗi User chỉ gửi được một Thư phản hồi");
        }

//      => SAVE
        Feedback newFeedback = repository.save(dto.changeToFeedbackInsert(optionalUserInfo.get()));
        return new ResponseDto<>(new FeedbackResponseDto(newFeedback));
    }

    @Override
    public ResponseDto<FeedbackResponseDto> updateStatus(int id, short status) {
        Optional<Feedback> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy thư phản hồi này");
        }

        Feedback modifiedFeedback = optional.get();
        modifiedFeedback.setStatus(status);
        repository.save(modifiedFeedback);

        return new ResponseDto<>(new FeedbackResponseDto(modifiedFeedback));
    }

    @Override
    public ResponseDto<FeedbackResponseDto> delete(Integer id) {
        Optional<Feedback> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy thư phản hồi này");
        }
        repository.delete(optional.get());
        return new ResponseDto<>(null);
    }



    @Override
    public ResponseDto<FeedbackResponseDto> getById(Integer id) {
        Optional<Feedback> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy thư phản hồi này");
        }
        return new ResponseDto<FeedbackResponseDto>(new FeedbackResponseDto(optional.get()));
    }

    @Override
    public ResponseByPageDto<List<FeedbackResponseDto>> listAll(int page, int size) {
        Pageable pageable =  PageRequest.of(page, size);
        Optional<Page<Feedback>> listOptional = Optional.ofNullable(repository.findAll(pageable));
        List<FeedbackResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách thư  phản hồi hiện đang rỗng");
        }
        for (Feedback feedback:listOptional.get()) {
            listResult.add(new FeedbackResponseDto(feedback));
        }
        return new ResponseByPageDto<>(listOptional.get().getTotalPages(), listResult);
    }
}
