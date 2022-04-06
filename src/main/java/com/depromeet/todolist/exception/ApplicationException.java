package com.depromeet.todolist.exception;

public class ApplicationException extends RuntimeException {

    private int defaultCode;

    public ApplicationException(int errorCode) {
        this.defaultCode = errorCode;
    }

    public int getErrorCode() {
        return this.defaultCode;
    }

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
        this.defaultCode = -1;
    }

    public ApplicationException(String message, int errorCode) {
        super(message);
        this.defaultCode = errorCode;
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.defaultCode = -1;
    }

    public ApplicationException(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.defaultCode = errorCode;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
        this.defaultCode = -1;
    }

    public ApplicationException(Throwable cause, int errorCode) {
        super(cause);
        this.defaultCode = errorCode;
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.defaultCode = -1;
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.defaultCode = errorCode;
    }
}
