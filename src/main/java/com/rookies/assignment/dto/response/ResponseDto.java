package com.rookies.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<E> {
    private int statusCode;
    private String message;
    private E   result;



    public ResponseDto( String message, E result) {
        this.result = result;
        this.message = message;
        this.statusCode = 200;
    }
    public ResponseDto(E result) {
        this.result = result;
        this.message = "thành công";
        this.statusCode = 200;
    }
    public ResponseDto(String message) {
        this.statusCode = 200;
        this.message = message;
        this.result = null;
    }
}
