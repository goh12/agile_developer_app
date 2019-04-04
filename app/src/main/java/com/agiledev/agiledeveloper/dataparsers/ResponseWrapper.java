package com.agiledev.agiledeveloper.dataparsers;

public class ResponseWrapper<T> {

    private boolean success;
    private String message;
    private T content;

    public  ResponseWrapper(boolean success, String message, T content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }


    /**
     * @return the content
     */
    public T getContent() {
        return this.content;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the success
     */
    public boolean getSuccess() {
        return this.success;
    }


}
