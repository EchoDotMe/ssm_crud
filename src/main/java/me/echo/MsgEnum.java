package me.echo;

public enum MsgEnum {
    OK(100, "success"), FAILED(101, "failed");

    private Integer code;
    private String msg;

    MsgEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
