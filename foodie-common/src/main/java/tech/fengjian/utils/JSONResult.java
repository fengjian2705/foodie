package tech.fengjian.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 200 成功
 * 500 表示错误，错误信息在msg中
 * 501 bean验证错误，不管多少个错误都以map返回
 * 502 拦截器拦截到用户token出错
 * 555 异常抛出信息
 * 556 用户qq校验异常
 */
public class JSONResult {

    // 定义jackson对象
    public static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public JSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public JSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static JSONResult errorMsg(String msg) {
        return new JSONResult(500, msg, null);
    }

    public static JSONResult ok() {
        return new JSONResult(null);
    }

    public static JSONResult ok(Object data) {
        return new JSONResult(data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
