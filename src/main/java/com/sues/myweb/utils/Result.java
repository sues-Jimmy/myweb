package com.sues.myweb.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Alan
 * @date 2022/11/21 0:11
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String errorMsg;
    private Object data;
    private Long total;

    public static Result ok() {
        return new Result(true, null, null, null);
    }

    public static Result ok(Object data) {
        return new Result(true, null, data, null);
    }

    public static Result ok(List<?> data, Long total) {
        return new Result(true, null, data, total);
    }
//这里返回一个Result的对象 由于RequestController含有ReponseBody注解能够解析我返回的对象
    public static Result fail(String errorMsg) {
        return new Result(false, errorMsg, null, null);
    }
}
