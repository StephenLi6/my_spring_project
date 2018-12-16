package com.theshy.exception;

/*
 * The Best Or Nothing
 * Desinger:TheShy
 * Date:2018/11/2621:33
 * com.theshy.exceptionMyDailyProject
 */
public class SysException extends Exception {
    // 存储异常提示信息
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public SysException(String message) {
        this.message = message;
    }
}
