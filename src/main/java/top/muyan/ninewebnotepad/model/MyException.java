package top.muyan.ninewebnotepad.model;

/**
 * 许映杰 2019/11/19
 */
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code = Result.CODE_NOMAL_ERROR;

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Exception e) {
        super(message, e);
    }

    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }

    public MyException(int code, String message, Exception e) {
        super(message, e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
