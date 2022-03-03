package com.xiong.result;

import lombok.Data;

/**
 * @author xiong
 * @version 1.0.0
 * @ClassName ResultBean.java
 * @Description TODO
 * @createTime 2022年03月03日 14:58:00
 */
@Data
public class Result {
    private int code;
    private Object data;
    private String msg;

    public static Result error(int code, String msg) {
        Result resultBean = new Result();
        resultBean.setCode(code);
        resultBean.setMsg(msg);
        return resultBean;
    }
    public static Result error(String msg) {
        Result resultBean = new Result();
        resultBean.setMsg(msg);
        return resultBean;
    }
    public static Result success(int code, String msg, Object data) {
        Result resultBean = new Result();
        resultBean.setMsg(msg);
        resultBean.setCode(code);
        resultBean.setData(data);
        return resultBean;
    }
    public static Result success(int code, Object data) {
        Result resultBean = new Result();
        resultBean.setCode(code);
        resultBean.setData(data);
        return resultBean;
    }
    public static Result success(Object data) {
        Result resultBean = new Result();
        resultBean.setData(data);
        return resultBean;
    }

    public static Result success(int code, String msg) {
        Result resultBean = new Result();
        resultBean.setCode(code);
        resultBean.setMsg(msg);
        return resultBean;
    }

}
