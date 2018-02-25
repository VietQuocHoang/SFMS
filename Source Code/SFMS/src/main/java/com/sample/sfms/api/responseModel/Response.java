package com.sample.sfms.api.responseModel;

public class Response {

    protected boolean succeed;
    protected String message;

    public Response(boolean succeed, String message) {
        this.succeed = succeed;
        this.message = message;
    }

    public Response(boolean succeed) {
        this.succeed = succeed;
    }

    public Response(){}

    public boolean isSucceed() {
        return succeed;
    }
}
