package top.muyan.ninewebnotepad.model;

/**
 * 许映杰 2019/11/6
 */
public class Result<T> {
    public static final int CODE_NOMAL_ERROR = 1;

    private int code;
    private T data;
    private String error;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
