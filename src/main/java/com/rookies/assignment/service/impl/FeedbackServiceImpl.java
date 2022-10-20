package com.rookies.assignment.service.impl;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.data.repository.IFeedbackRepository;
import com.rookies.assignment.data.repository.IUserInfoRepository;
import com.rookies.assignment.dto.request.FeedbackRequestDto;
import com.rookies.assignment.dto.response.FeedbackResponseDto;
import com.rookies.assignment.dto.response.ResponseDto;
import com.rookies.assignment.exceptions.ResourceFoundException;
import com.rookies.assignment.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private IFeedbackRepository repository;
    @Autowired
    private IUserInfoRepository userRepository;

    @Override
    public ResponseDto<FeedbackResponseDto> insert(FeedbackRequestDto feedback) {
        Optional<UserInfo> optionalUserInfo = userRepository.findById(feedback.getUser_id());
//        check User not Empty
        if(optionalUserInfo.isEmpty()){
            throw new ResourceFoundException("User này không tồn tại");
        }
//        check if the user has submitted feedback today?
        if(feedback.isFirstFeedbackToday(optionalUserInfo.get().getListFeedbacks())){
            throw new ResourceFoundException("một ngày mỗi User chỉ gửi được một Thư phản hồi");
        }
//      => SAVE
        Feedback modifiedFeedback = feedback.changeToFeedbackInsert();
        repository.save(modifiedFeedback);
        return new ResponseDto<>(new FeedbackResponseDto(modifiedFeedback));
    }

    @Override
    public ResponseDto<FeedbackResponseDto> updateStatus(FeedbackRequestDto feedback) {
        Optional<Feedback> optional = repository.findById(feedback.getId());
        if(optional.isEmpty()){
            throw new ResourceFoundException("Không tìm thấy thư phản hồi này");
        }
        Feedback modifiedFeedback = feedback.changeToFeedbackUpdateStatus();
        Feedback newFeedback = repository.save(modifiedFeedback);
        return new ResponseDto<>(new FeedbackResponseDto(newFeedback));
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
    public ResponseDto<List<FeedbackResponseDto>> listAll() {
        Optional<List<Feedback>> listOptional = Optional.ofNullable(repository.findAll());
        List<FeedbackResponseDto> listResult = new ArrayList<>();
        if(listOptional.isEmpty()){
            throw new ResourceFoundException("Danh sách thư  phản hồi hiện đang rỗng");
        }
        for (Feedback feedback:listOptional.get()) {
            listResult.add(new FeedbackResponseDto(feedback));
        }
        return new ResponseDto<>(listResult);
    }
}
