package org.vatalu.model.entity;


public class User extends CommonUser{
    private String salt;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User(Long id,String userName, String password, String level, String salt) {
        super(id,userName,level);
        this.password = password;
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
