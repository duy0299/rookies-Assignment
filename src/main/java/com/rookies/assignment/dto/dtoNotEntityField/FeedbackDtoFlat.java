package com.rookies.assignment.dto.dtoNotEntityField;

import com.rookies.assignment.entity.Feedback;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.entity.Feedback} entity
 */
@Data
@NoArgsConstructor
public class FeedbackDtoFlat{
    private  int id;
    private  int content;
    private  short status;
    private  Timestamp time_create;
    private  Timestamp time_update;

    public FeedbackDtoFlat(Feedback feedback) {
        id = feedback.getId();
        content = feedback.getContent();
        status = feedback.getStatus();
        time_create = feedback.getTime_create();
        time_update = feedback.getTime_update();
    }
}