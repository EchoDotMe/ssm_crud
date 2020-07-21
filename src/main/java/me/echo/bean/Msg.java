package me.echo.bean;

import me.echo.MsgEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回客户端的类
 */
public class Msg {
    private int code;

    private String msg;

    private Map<String, Object> extend = new HashMap<>();

    public Msg() {
    }

    public Msg(int code, String msg, Map<String, Object> extend) {
        this.code = code;
        this.msg = msg;
        this.extend = extend;
    }

    public static Msg success(){
        Msg msg = new Msg();
        msg.setCode(MsgEnum.OK.getCode());
        msg.setMsg(MsgEnum.OK.getMsg());
        return msg;
    }

    public static Msg fail(){
        Msg msg = new Msg();
        msg.setCode(MsgEnum.FAILED.getCode());
        msg.setMsg(MsgEnum.FAILED.getMsg());
        return msg;
    }

    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
