package webMailApp.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 14.02.13
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"addressName"}), name = "Address")
public class AddressDTO implements Serializable {

    @Id
    @GeneratedValue
    private long addressID;

    @Column(name = "addressDate", nullable = false)
    private Date addressDate;

    @Column(name = "addressName", nullable = false, length = 75)
    private String addressName;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDTO addressUser;

    @OneToMany(mappedBy = "folderAddress", cascade = CascadeType.ALL)
    private List<FolderDTO> addressFolder;

    @OneToMany(mappedBy = "letterFrom", cascade = CascadeType.ALL)
    private List<LetterDTO> addressSendedLetters;

    public AddressDTO() {
    }

    public AddressDTO(Date addressDate, String addressName, UserDTO addressUser) {
        this.addressDate = addressDate;
        this.addressName = addressName;
        this.addressUser = addressUser;
    }


}
