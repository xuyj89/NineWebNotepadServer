package top.muyan.ninewebnotepad.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.muyan.ninewebnotepad.model.MyException;
import top.muyan.ninewebnotepad.model.Result;

import javax.servlet.http.HttpServletRequest;


/**
 * 全局错误处理
 * @author 许映杰
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * 自定义错误处理
	 * @param request
	 * @param ex
	 * @return
	 */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result<String> handleryException(HttpServletRequest request, MyException ex) {
        //打印错误日志
    	logger.error(ex.getMessage(),ex);
    	
    	//将错误信息返回给前端
        Result<String> result = new Result<>();
        result.setCode(ex.getCode());
        result.setError(ex.getMessage());
        return result;
    }
    
    /**
     * 其他错误处理
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> handlerException(HttpServletRequest request, Exception ex){
    	//打印错误日志
        logger.error(ex.getMessage(),ex);
    	
    	//将错误信息返回给前端
        Result<String> resultData = new Result<>();
        resultData.setCode(Result.CODE_NOMAL_ERROR);
        resultData.setError("服务器出现错误...");
        return resultData;
    }
}
