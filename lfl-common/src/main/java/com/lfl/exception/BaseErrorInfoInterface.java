package com.lfl.exception;

/**
 * @Title: BaseErrorInfoInterface
 * @Description: 基础接口
 * 自定义的错误描述枚举类需实现该接口
 */
public interface BaseErrorInfoInterface {
    /**
     * 错误码
     */
    String getResultCode();

    /**
     * 错误描述
     */
    String getResultMsg();
}
