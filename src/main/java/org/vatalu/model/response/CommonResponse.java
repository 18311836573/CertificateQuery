package org.vatalu.model.response;

public class CommonResponse {

    protected Boolean result;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public CommonResponse(Boolean result) {
        this.result = result;
    }
}
