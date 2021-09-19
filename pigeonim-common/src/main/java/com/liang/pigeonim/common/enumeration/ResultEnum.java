package com.liang.pigeonim.common.enumeration;

import com.liang.pigeonim.common.entity.ApiResult;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 8:46 AM
 */
public enum ResultEnum implements ApiResult {

    // 公共基础服务类异常
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    NOT_LOGIN(2, "用户未登录"),
    SMS_SEND_ERROR(3, "发送短信失败"),
    SYSTEM_BUSY(4, "系统繁忙，请稍后再试"),
    INNER_INVOKE_ERROR(5, "内部调用错误"),
    VALIDATION_FAIL(6, "参数校验失败"),

    CREDENTIALS_ERROR(1008,"您的登录状态已过期，请重新登录"),

    COMMON_ERROR(2001, "内部错误"),
    WX_NOT_LOGIN(2002, "微信未登录"),
    PHONE_NOT_LOGIN(2003, "尚未登录"),
    FORBID_SMS_CODE(2004, "禁止重复发送验证码"),
    LOGIN_ERROR(2005, "登录失败"),
    PASSWORD_NOT_SAME(2006, "两次输入的密码不一致，请重新输入"),
    WX_INVOKE_ERROR(2007, "调用微信失败"),
    PHONE_USED(2008, "手机号已被使用"),
    PHONE_NOT_EXISTS_DISABLE(2009, "该用户不存在或未启用"),
    SMS_CODE_ERROR(2010, "输入验证码有误，请核实验证码"),
    SMS_CODE_TIMEOUT(2011, "验证码已经失效，请重新获取验证码"),
    RESET_PWD_TIMEOUT(2012, "重置密码超时"),
    RESET_PWD_TOKEN_ERROR(2013, "重置密码token错误"),
    MEMBER_FORBID(2014, "该手机号已被禁用，请联系客服"),
    REPEAT_LOGIN(2015, "请勿重复登录"),
    NOT_USER(2015, "此账号不存在"),
    OFF_LINE(2016, "账号已下线"),
    RECONNECT_FAIL(2017, "重复连接失败"),
    ACCOUNT_NOT_MATCH(2018, "登录信息不匹配"),
    SERVER_NOT_AVAILABLE(2019, "无可用服务"),
    ;

    int code;
    String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultEnum getMsgByCode(int code) {
        for (ResultEnum result : values()) {
            if (result.getCode() == code) {
                return result;
            }
        }
        return null;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
