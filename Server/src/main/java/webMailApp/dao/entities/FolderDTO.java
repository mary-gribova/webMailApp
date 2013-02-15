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
public class FolderDTO implements Serializable {

    @Id
    @GeneratedValue
    private long folderID;

    @Column(name = "folderName", nullable = false)
    private String folderName;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressDTO folderAddress;

    @OneToMany(mappedBy = "letterFolder", cascade = CascadeType.ALL)
    private List<LetterDTO> folderLetters;

    public FolderDTO() {
    }

    public FolderDTO(String folderName, AddressDTO folderAddress) {
        this.folderName = folderName;
        this.folderAddress = folderAddress;
    }
}
