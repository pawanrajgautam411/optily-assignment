package com.optily.assignment.boot;

import com.optily.assignment.vo.ResponseVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

/**
 *
 */
@RestControllerAdvice
public class DefaultControllerAdvice {

    /**
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ResponseVo> defaultExceptionHandler(Throwable e) {

        ResponseVo responseVo =
                new ResponseVo(Arrays.asList(e.getMessage()));

        return new ResponseEntity<ResponseVo>(responseVo,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
