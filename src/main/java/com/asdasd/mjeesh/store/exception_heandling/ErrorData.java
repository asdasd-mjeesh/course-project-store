package com.asdasd.mjeesh.store.exception_heandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorData {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime time;
    private String message;
    private String error;

    public ErrorData(HttpStatus status, Throwable ex) {
        this.status = status;
        this.time = LocalDateTime.now();
        this.message = ex.getMessage();
        this.error = ex.getClass().getSimpleName();
    }
}
