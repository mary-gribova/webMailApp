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
@NamedQuery(name = "Address.findByName",
        query = "SELECT a FROM AddressEntity a where a.addressName = :addressName")
public class AddressEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long addressID;

    @Column(name = "addressDate", nullable = false)
    private Date addressDate;

    @Column(name = "addressName", nullable = false, length = 75)
    private String addressName;

    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity addressUser;

    @OneToMany(mappedBy = "folderAddress", cascade = CascadeType.ALL)
    private List<FolderEntity> addressFolder;

    @OneToMany(mappedBy = "letterFrom", cascade = CascadeType.ALL)
    private List<LetterEntity> addressSendedLetters;

    @OneToMany(mappedBy = "letterTo", cascade = CascadeType.ALL)
    private List<LetterEntity> addressRecievedLetters;

    public AddressEntity() {
    }

    public AddressEntity(Date addressDate, String addressName, UserEntity addressUser) {
        this.addressDate = addressDate;
        this.addressName = addressName;
        this.addressUser = addressUser;
    }

    public void setAddressID(long addressID) {
        this.addressID = addressID;
    }

    public void setAddressDate(Date addressDate) {
        this.addressDate = addressDate;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public void setAddressUser(UserEntity addressUser) {
        this.addressUser = addressUser;
    }

    public void setAddressFolder(List<FolderEntity> addressFolder) {
        this.addressFolder = addressFolder;
    }

    public void setAddressSendedLetters(List<LetterEntity> addressSendedLetters) {
        this.addressSendedLetters = addressSendedLetters;
    }

    public long getAddressID() {
        return addressID;
    }

    public Date getAddressDate() {
        return addressDate;
    }

    public String getAddressName() {
        return addressName;
    }

    public UserEntity getAddressUser() {
        return addressUser;
    }

    public List<FolderEntity> getAddressFolder() {
        return addressFolder;
    }

    public List<LetterEntity> getAddressSendedLetters() {
        return addressSendedLetters;
    }

    public List<LetterEntity> getAddressRecievedLetters() {
        return addressRecievedLetters;
    }

    public void setAddressRecievedLetters(List<LetterEntity> addressRecievedLetters) {
        this.addressRecievedLetters = addressRecievedLetters;
    }
}
