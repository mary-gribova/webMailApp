package webMailApp.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 14.02.13
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "Folder")
@NamedQuery(name = "Folder.findByAddressAndName",
            query = "select f from FolderEntity f where f.folderAddress.addressName = :addressName and f.folderName = :folderName")
public class FolderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long folderID;

    @Column(name = "folderName")
    private String folderName;

    @ManyToOne(cascade = CascadeType.ALL)
    private EmailEntity folderAddress;

    @OneToMany(mappedBy = "letterFolder", cascade = CascadeType.ALL)
    private List<LetterEntity> folderLetters;

    public FolderEntity() {
    }

    public FolderEntity(String folderName, EmailEntity folderAddress) {
        this.folderName = folderName;
        this.folderAddress = folderAddress;
    }


    public long getFolderID() {
        return folderID;
    }

    public String getFolderName() {
        return folderName;
    }

    public EmailEntity getFolderAddress() {
        return folderAddress;
    }


    public void setFolderID(long folderID) {
        this.folderID = folderID;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setFolderAddress(EmailEntity folderAddress) {
        this.folderAddress = folderAddress;
    }

    public List<LetterEntity> getFolderLetters() {
        return folderLetters;
    }

    public void setFolderLetters(List<LetterEntity> folderLetters) {
        this.folderLetters = folderLetters;
    }

    public void addLetter(LetterEntity letter) {
        folderLetters.add(letter);
    }
}
