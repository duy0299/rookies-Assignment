package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
public class FeedbackResponseDto extends FeedbackDtoFlat {
    private  UserInfoDtoFlat userInfo;

    public FeedbackResponseDto(Feedback feedback) {
        super(feedback);

        userInfo = new UserInfoDtoFlat(feedback.getUserInfo());

    }
}