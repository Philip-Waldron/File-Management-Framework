package com.studentportal.exceptions;

public class ExceptionMessage {

    private String message;
    private int statusCode;
//    private String documentation;

    public ExceptionMessage() {
        super();
    }

    public ExceptionMessage(String message, int statusCode /*String documentation*/) {
        super();
        this.message = message;
        this.statusCode = statusCode;
//        this.documentation = documentation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

//    public String getDocumentation() {
//        return documentation;
//    }
//
//    public void setDocumentation(String documentation) {
//        this.documentation = documentation;
//    }
}
