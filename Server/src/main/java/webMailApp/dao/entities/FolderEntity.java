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
public class FolderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long folderID;

    @Column(name = "folderName")
    private String folderName;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity folderAddress;

//    @OneToMany(mappedBy = "letterFolder", cascade = CascadeType.ALL)
//    private List<LetterEntity> folderLetters;

    public FolderEntity() {
    }

    public FolderEntity(String folderName, AddressEntity folderAddress) {
        this.folderName = folderName;
        this.folderAddress = folderAddress;
    }


    public long getFolderID() {
        return folderID;
    }

    public String getFolderName() {
        return folderName;
    }

    public AddressEntity getFolderAddress() {
        return folderAddress;
    }


    public void setFolderID(long folderID) {
        this.folderID = folderID;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setFolderAddress(AddressEntity folderAddress) {
        this.folderAddress = folderAddress;
    }


}
