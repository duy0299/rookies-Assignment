package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.data.entity.Product;
import com.rookies.assignment.data.entity.Size;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import com.rookies.assignment.dto.flat.UserInfoDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
public class FeedbackRequestDto extends FeedbackDtoFlat {
    private UUID user_id;

    public Feedback changeToFeedbackInsert(){
        Feedback feedback = new Feedback();
        UserInfo userInfo = new UserInfo();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        userInfo.setId(user_id);

        feedback.setStatus((short) 1);
        feedback.setUserInfo(userInfo);

        feedback.setContent(getContent());
        feedback.setTimeCreate(now);
        feedback.setTimeUpdate(now);

        return feedback;
    }

    public Feedback changeToFeedbackUpdateStatus(Feedback oldFeedback){
        Feedback feedback = new Feedback();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        feedback.setStatus(getStatus());
        feedback.setId(getId());
        feedback.setTimeUpdate(now);
        feedback.setUserInfo(oldFeedback.getUserInfo());
        feedback.setContent(oldFeedback.getContent());
        feedback.setTimeCreate(oldFeedback.getTimeCreate());

        return feedback;
    }

    public boolean isFirstFeedbackToday(List<Feedback> listFeedback){
        if(listFeedback == null){
            return true;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        String strNow =  formatter.format(now);
        for (Feedback feedback : listFeedback) {
            String strTimeCreate = formatter.format(feedback.getTimeCreate());
            if(strTimeCreate.equals(strNow)) {
                return false;
            }
        }
        return true;
    }
}