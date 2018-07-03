package com.open.coinnews.web.controller.basic;

import com.open.coinnews.basic.exception.ErrorInfo;
import com.open.coinnews.basic.exception.RequestLimitException;
import com.open.coinnews.basic.exception.SystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SystemException.class)
    public String systemExceptionHandler(Model model, HttpServletRequest req, SystemException e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "errors/system";
    }

    @ExceptionHandler(value = RequestLimitException.class)
    public String requestLimitExceptionHandler(Model model, HttpServletRequest req, RequestLimitException e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
//        er.setUrl(req.getRequestURL().toString());
//        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
        return "errors/default";
    }

    @ExceptionHandler(value = Exception.class)
    public String defaultExceptionHandler(Model model, HttpServletRequest req, Exception e) {
        ErrorInfo<String> er = new ErrorInfo<>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setDatas("发生异常，无法继续进行！");
        model.addAttribute("errorInfo", er);
//        e.printStackTrace();
        return "errors/default";
    }
}
