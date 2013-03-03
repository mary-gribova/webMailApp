package webMailApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * UserEntity: maru
 * Date: 14.02.13
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "User")
@NamedQueries({
@NamedQuery(name = "User.findByAddressName",
            query = "select u from UserEntity u where u.userAddress.addressName = :addressName"),
})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long userID;

     @Column(name = "userFirstName", length = 45)
     private String userFirstName;

    @Column(name = "userLastName", length = 45)
    private String userLastName;

    @Column(name = "userBirthDate")
    private Date userBirthDate;

    @Column(name = "userPhone")
    private String userPhone;

    @Column(name = "userPassword")
    private String userPassword;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "addressUser")
    private EmailEntity userAddress;

    public UserEntity() {
    }

    public UserEntity(String userFirstName, String userLastName, Date userBirthDate, String userPhone, String userPassword) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userBirthDate = userBirthDate;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public long getUserID() {
        return userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public EmailEntity getUserAddress() {
        return userAddress;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserAddress(EmailEntity userAddress) {
        this.userAddress = userAddress;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
