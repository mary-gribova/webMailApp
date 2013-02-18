package webMailApp.dao.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 17.02.13
 * Time: 2:23
 * To change this template use File | Settings | File Templates.
 */
public class LoginDTO implements Serializable {
    private String userPassword;
    private String userAddress;

    public LoginDTO() {
    }

    public LoginDTO(String userPassword, String userAddress) {
        this.userPassword = userPassword;
        this.userAddress = userAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
