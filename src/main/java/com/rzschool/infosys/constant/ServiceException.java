package com.rzschool.infosys.constant;

import com.rzschool.infosys.result.CodeMsg;

public class ServiceException extends RuntimeException {

    private CodeMsg codeMsg;

    public ServiceException(String message) {
        super(message);
        this.codeMsg = CodeMsg.FAILURE;
    }

    public ServiceException(CodeMsg codeMsg) {
        super(codeMsg.getMessage());
        this.codeMsg = codeMsg;
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }

    public CodeMsg getCodeMsg() {
        return this.codeMsg;
    }

}
