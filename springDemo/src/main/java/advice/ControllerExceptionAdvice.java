package advice;

import com.example.springdemo.entity.ResultVo;
import com.example.springdemo.exception.BaseException;
import com.example.springdemo.inter.em.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: br
 * @author: wang.chengcheng
 * @create: 2023-09-12 17:08
 **/
@RestControllerAdvice(basePackages = {"com.example.springdemo.controller"})
public class ControllerExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResultVo handle(BaseException e) {
       return new ResultVo(e.getCode(), e.getMsg(), e.getMessage());

    }

    @ExceptionHandler(BindException.class)
    public ResultVo handle(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVo(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }
}
