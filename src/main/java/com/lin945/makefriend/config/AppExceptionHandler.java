package com.lin945.makefriend.config;

import com.lin945.makefriend.pojo.ResultBody;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin945
 * @date 2020/11/24 18:15
 * @description
 */
@RestControllerAdvice
public class AppExceptionHandler {
    // 通用异常处理
    @ExceptionHandler(Exception.class)
    public ResultBody error(Exception e) {
        return ResultBody.error().message(e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultBody resolveBindException(BindException e){
        List<String> errorList = new ArrayList<>();
        BindingResult result = e.getBindingResult();
        if (result.hasErrors()) {
            for (ObjectError allError : result.getAllErrors()) {
                errorList.add(allError.getDefaultMessage());
            }
        }
        ResultBody failure = ResultBody.error();
               failure .setData(errorList);
        return failure;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultBody resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errorList = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()){
            for (ObjectError allError : result.getAllErrors()) {
                String defaultMessage = allError.getDefaultMessage();
                errorList.add(defaultMessage);
            }
        }
        ResultBody failure = ResultBody.error();
        failure .setData(errorList);
        return failure;
    }
}
