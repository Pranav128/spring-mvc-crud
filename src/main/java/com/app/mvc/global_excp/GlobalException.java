package com.app.mvc.global_excp;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalException {
    // Specific Exception Handling
    @ExceptionHandler(NoResourceFoundException.class)
    public ModelAndView handleCustomException(HttpServletRequest request, NoResourceFoundException ex,Model map) {
        map.addAttribute("exception", ex);
        map.addAttribute("url", request.getRequestURL());
        map.addAttribute("ex", ex.toString());
        ModelAndView view = new ModelAndView();
        view.setViewName("custom-error");
//        ex.printStackTrace();
        return view ;
    }

    // Generic Exception Handling
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(HttpServletRequest request, Exception ex, Model map) {
        map.addAttribute("exception", ex);
        map.addAttribute("url", request.getRequestURL());
        map.addAttribute("ex", ex.toString());
        ModelAndView view = new ModelAndView();
        view.setViewName("custom-error");
//        ex.printStackTrace();
        return view ;
    }

}
