package webMailApp.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * UserDTO: maru
 * Date: 14.02.13
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"userFirstName", "userLastName" }),
        name = "User")
public class UserDTO implements Serializable {

    @Id
    @GeneratedValue
    private long userID;

    @Column(name = "userFirstName", nullable = false, length = 45)
     private String userFirstName;

    @Column(name = "userLastName", nullable = false, length = 45)
    private String userLastName;

    @Column(name = "userBirthDate", nullable = false)
    private Date userBirthDate;

    @Column(name = "userPhone", nullable = false)
    private String userPhone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "addressUser")
    private AddressDTO userAddress;


    public UserDTO() {
    }

    public UserDTO(String userFirstName, String userLastName, Date userBirthDate, String userPhone, AddressDTO userAddress) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userBirthDate = userBirthDate;
        this.userPhone = userPhone;
        this.userAddress = userAddress;
    }
}
