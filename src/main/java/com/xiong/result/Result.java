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

    public Result(){ }

    public Result(int code,String msg,Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result error(int code, String msg) {

        return new Result(code,msg,null);
    }

    public static Result error(String msg) {

        return new Result(0,msg,null);
    }

    public static Result success(int code, String msg, Object data) {

        return new Result(code,msg,data);
    }

    public static Result success(int code, Object data) {

        return new Result(code,null,data);
    }

    public static Result success(Object data) {

        return new Result(0,null,data);
    }

    public static Result success(int code, String msg) {

        return new Result(code,msg,null);
    }

    @Override
    public String toString(){
        return "Result [code=" + code + ", data=" + data + ", msg=" + msg + "]";
    }

}
