package huahua.common;

import lombok.Data;

/**
 * 公共的返回类
 */
@Data
public class Result {
    //是否成功
    private boolean flag;
    //状态码
    private Integer code;
    //请求信息
    private String message;
    //响应的数据
    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
