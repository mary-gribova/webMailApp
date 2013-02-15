package webMailApp.dao.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public class UserDTO implements Serializable {
    private String userFirstName;
    private String userLastName;
    private String userPhone;
    private String userAddress;
    private Date userBirthDate;
    private String userPass;

    public UserDTO(String userFirstName, String userLastName, String userPass, Date userBirthDate,
                   String userPhone, String userAddress) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPass = userPass;
        this.userBirthDate = userBirthDate;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public String getUserPass() {
        return userPass;
    }
}
