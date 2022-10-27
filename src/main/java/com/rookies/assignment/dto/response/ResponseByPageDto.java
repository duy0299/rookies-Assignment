package com.rookies.assignment.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseByPageDto<E> extends ResponseDto{
    private int totalPage;


    public ResponseByPageDto(int totalPage, E element) {
        super();
        this.totalPage = totalPage;
        setResult(element);
        setMessage("thành công");
        setStatusCode("200");
    }
}
