package org.vatalu.model.response;

public class UploadFileResponse extends CommonResponse {
    private String message;

    public UploadFileResponse(Boolean result) {
        super(result);
    }

    public UploadFileResponse(Boolean result, String message) {
        super(result);
        this.message = message;
    }
}
