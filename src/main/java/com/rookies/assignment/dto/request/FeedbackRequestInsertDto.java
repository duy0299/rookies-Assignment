package com.rookies.assignment.dto.request;

import com.rookies.assignment.data.entity.Feedback;
import com.rookies.assignment.data.entity.UserInfo;
import com.rookies.assignment.dto.flat.FeedbackDtoFlat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class FeedbackRequestInsertDto {
    private String content;


    public FeedbackRequestInsertDto(String content){
        this.content = content;
    }
    public Feedback changeToFeedbackInsert(UserInfo userInfo){
        Feedback feedback = new Feedback();
        Date dateNow = new Date();
        Timestamp now = new Timestamp(dateNow.getTime());

        feedback.setStatus((short) 1);
        feedback.setUserInfo(userInfo);
        feedback.setContent(content);
        feedback.setTimeCreate(now);
        feedback.setTimeUpdate(now);

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