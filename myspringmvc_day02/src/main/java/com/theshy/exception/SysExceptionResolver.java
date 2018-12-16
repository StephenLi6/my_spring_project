package com.theshy.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2621:34
 * com.theshy.exceptionMyDailyProject
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {
        SysException e = null;
        if (ex instanceof SysException){
            e = (SysException)ex;
        }else {
            e = new SysException("系統正在維護。。。");
        }
        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        // 从异常对象中获取到提示的信息
        mv.addObject("errorMsg", e.getMessage());
        // 跳转错误页面
        mv.setViewName("error");
        return mv;
    }
}
