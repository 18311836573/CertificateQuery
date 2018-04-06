package org.vatalu.model.response;

public class LoginResponse extends CommonResponse{
    private String userName;
    private String level;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LoginResponse(Boolean result) {
        super(result);
    }

    public LoginResponse(Boolean result, String userName, String level) {
        super(result);
        this.userName = userName;
        this.level = level;
    }
}
