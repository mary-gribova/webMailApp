package webMailApp.dao.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 14.02.13
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Letter")
public class LetterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long letterID;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity letterFrom;


    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity letterTo;

    @Column(name = "letterDate", nullable = false)
    private Date letterDate;

    @Column(name = "letterTheme", length = 55, nullable = true)
    private String letterTheme;


    @Column(name = "letterBody", columnDefinition = "LONGTEXT")
    private String letterBody;

    @ManyToOne
    private FolderEntity letterFolder;

    public LetterEntity() {
    }

    public long getLetterID() {
        return letterID;
    }

    public AddressEntity getLetterFrom() {
        return letterFrom;
    }

    public AddressEntity getLetterTo() {
        return letterTo;
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public String getLetterTheme() {
        return letterTheme;
    }

    public String getLetterBody() {
        return letterBody;
    }

    public void setLetterID(long letterID) {
        this.letterID = letterID;
    }

    public void setLetterFrom(AddressEntity letterFrom) {
        this.letterFrom = letterFrom;
    }

    public void setLetterTo(AddressEntity letterTo) {
        this.letterTo = letterTo;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public void setLetterTheme(String letterTheme) {
        this.letterTheme = letterTheme;
    }

    public void setLetterBody(String letterBody) {
        this.letterBody = letterBody;
    }

    public FolderEntity getLetterFolder() {
        return letterFolder;
    }

    public void setLetterFolder(FolderEntity letterFolder) {
        this.letterFolder = letterFolder;
    }
}

