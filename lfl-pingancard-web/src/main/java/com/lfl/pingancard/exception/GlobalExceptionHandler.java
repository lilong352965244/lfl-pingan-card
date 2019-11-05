package com.lfl.pingancard.exception;

import com.lfl.enums.CommonEnum;
import com.lfl.exception.CustomException;
import com.lfl.response.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;


/**
 * @Title: GlobalExceptionHandler
 * @Description: 全局异常处理
 * 如果使用@RestControllerAdvice 注解
 * 则会将返回的数据类型转换成JSON格式
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResultBody bizExceptionHandler(HttpServletRequest req, CustomException e) {
        log.error("发生业务异常！原因是：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常！原因是:", e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResultBody handleExceptionInternal(HttpServletRequest req, Exception e) {
        log.error("请求参数异常！原因是:", e);

        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) e;
            return ResultBody.error(CommonEnum.BODY_NOT_MATCH.getResultCode(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }
        if (e instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) e;
            log.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            return ResultBody.error(CommonEnum.BODY_NOT_MATCH.getResultCode(), "参数转换失败");
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }


    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR.getResultCode(),e.getMessage());
    }





}
