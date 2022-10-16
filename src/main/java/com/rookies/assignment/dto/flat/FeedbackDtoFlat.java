package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Feedback;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * A DTO for the {@link com.rookies.assignment.data.entity.Feedback} entity
 */
@Data
@NoArgsConstructor
public class FeedbackDtoFlat{
    private  int id;
    private  int content;
    private  short status;
    private  Timestamp timeCreate;
    private  Timestamp timeUpdate;

    public FeedbackDtoFlat(Feedback feedback) {
        id = feedback.getId();
        content = feedback.getContent();
        status = feedback.getStatus();
        timeCreate = feedback.getTimeCreate();
        timeUpdate = feedback.getTimeUpdate();
    }
}