package org.vatalu.model.entity;

public class CommonUser {
    protected Long id;
    protected String userName;
    protected String level;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommonUser(Long id, String userName, String level) {
        this.userName = userName;
        this.level = level;
        this.id = id;
    }

    public CommonUser(User user) {
        this.userName = user.getUserName();
        this.level = user.getLevel();
        this.id = user.getId();
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
