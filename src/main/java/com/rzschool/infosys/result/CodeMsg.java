package com.rzschool.infosys.result;

import lombok.Getter;

/**
 * 错误代码
 */
@Getter
public class CodeMsg<T> {


    private int code;
    private String message;
    private T data;

    // 通用模块 5001XX
    public static CodeMsg SUCCESS = new CodeMsg(1, "success");
    public static CodeMsg FAILURE = new CodeMsg(0, "failure");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg UPLOAD_FAILED = new CodeMsg(500102, "文件上传失败");
    public static CodeMsg METHOD_NOT_SUPPORT = new CodeMsg(500103, "Request method '%s' not supported");
    public static CodeMsg ENTITY_NOT_EXIST = new CodeMsg(500104, "数据记录不存在");
    public static String OPERATE_SUCCESS = "操作成功";

    // 用户相关
    public static CodeMsg TOKEN_KEY_ERROR = new CodeMsg(900100, "签发动态秘钥失败");
    public static CodeMsg TOKEN_KEY_EXPIRED = new CodeMsg(900101, "TOKEN_KEY过期");
    public static CodeMsg TOKEN_EXPIRED = new CodeMsg(900102, "TOKEN过期");
    public static CodeMsg REFRESH_TOKEN_EXPIRED = new CodeMsg(900103, "REFRESH_TOKEN过期");
    public static CodeMsg TOKEN_ERROR = new CodeMsg(900104, "错误的TOKEN");
    public static CodeMsg USER_NOT_EXIST = new CodeMsg(900105, "用户不存在");
    public static CodeMsg ERROR_AUTHORIZATION = new CodeMsg(900106, "未授权");
    public static CodeMsg USER_ERROR = new CodeMsg(900107, "用户不正确");
    public static CodeMsg PWD_REPEAT_ERROR = new CodeMsg(900108, "两次密码不相同");
    public static CodeMsg USER_DISABLED = new CodeMsg(900109, "用户被禁用");
    public static CodeMsg USER_EXIST = new CodeMsg(900110, "此用户已存在");


    //权限模块
    public static CodeMsg NO_AUTHORIZATION = new CodeMsg(401, "未授权");
    public static CodeMsg INVALID = new CodeMsg(502, "无效请求");
    public static CodeMsg AUTHORIZATION_NOT_EXIT = new CodeMsg(503, "无匹配数据");

    private CodeMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private CodeMsg(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * 带参数的CodeMsg
     *
     * @param args 待填充的参数
     * @return 组装好的CodeMsg
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new CodeMsg(code, message);
    }

    public CodeMsg<T> addData(T data) {
        int code = this.code;
        String message = this.message;
        return new CodeMsg(code, message, data);
    }
}
