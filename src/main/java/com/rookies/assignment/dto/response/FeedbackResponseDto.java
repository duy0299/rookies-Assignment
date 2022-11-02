package com.rookies.assignment.dto.response;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
public class FeedbackResponseDto {
    private  int id;
    @NotEmpty
    @NotNull
    @Size(min = 1,  max = 200)
    private  String content;
    private  short status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;
    private  UserInfoDtoFlat userInfo;

    public FeedbackResponseDto(Feedback feedback) {
        id = feedback.getId();
        content = feedback.getContent();
        status = feedback.getStatus();
        timeCreate = feedback.getTimeCreate();
        timeUpdate = feedback.getTimeUpdate();
        userInfo = new UserInfoDtoFlat(feedback.getUserInfo());

    }
}