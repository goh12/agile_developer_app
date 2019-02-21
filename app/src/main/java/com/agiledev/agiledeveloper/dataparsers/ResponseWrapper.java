package com.agiledev.agiledeveloper.dataparsers;

public class ResponseWrapper {

    private boolean success;
    private String message;
    private Object content;

    public  ResponseWrapper(boolean success, String message, Object content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }


    /**
     * @return the content
     */
    public Object getContent() {
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
