package com.rookies.assignment.dto.flat;

import com.rookies.assignment.data.entity.Feedback;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class FeedbackDtoFlat{
    private  int id;
    @NotEmpty
    @NotNull
    @Size(min = 1,  max = 200)
    private  String content;
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