package com.rookies.assignment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<E> {
    private String statusCode;
    private String message;
    private E   result;

    public ResponseDto(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.result = null;
    }

    public ResponseDto( String message, E result) {
        this.result = result;
        this.message = message;
        this.statusCode = "00";
    }

    public ResponseDto(String message) {
        this.statusCode = "00";
        this.message = message;
        this.result = null;
    }
}
