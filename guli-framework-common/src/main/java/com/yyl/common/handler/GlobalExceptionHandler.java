package com.yyl.common.handler;

import com.yyl.common.constants.ResultCodeEnum;
import com.yyl.common.exception.GuliException;
import com.yyl.common.util.ExceptionUtil;
import com.yyl.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice  //统一异常处理器
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){

        e.printStackTrace(); //输出异常堆栈信息
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e){

//        e.printStackTrace(); //输出异常堆栈信息
//        return Result.error().code(20003).message("sql语法错误");
        log.error(e.getMessage());
        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(GuliException.class)
    @ResponseBody
    public Result error(GuliException e){

//        e.printStackTrace(); //输出异常堆栈信息
//        log.error(e.getMessage());
        log.error(ExceptionUtil.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());
    }
}
