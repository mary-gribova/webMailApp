package webMailApp.dto;

import java.io.Serializable;


/**
 * @author Mariia Gribova
 * @version 1.0
 *
 * Login transfer object
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
