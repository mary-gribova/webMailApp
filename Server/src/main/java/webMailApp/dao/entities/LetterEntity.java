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
@NamedQueries({
@NamedQuery(name = "Letter.findLetter",
            query = "select l from LetterEntity l where l.letterFrom.addressName = :letterFrom and l.letterTo.addressName = :letterTo" +
                    " and l.letterDate = :letterDate and l.letterTheme = :letterTheme"),
@NamedQuery(name = "Letter.delete", query = "delete from LetterEntity l where l = :letter")

})
public class LetterEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long letterID;

    @ManyToOne
    private EmailEntity letterFrom;


    @ManyToOne
    private EmailEntity letterTo;

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

    public EmailEntity getLetterFrom() {
        return letterFrom;
    }

    public EmailEntity getLetterTo() {
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

    public void setLetterFrom(EmailEntity letterFrom) {
        this.letterFrom = letterFrom;
    }

    public void setLetterTo(EmailEntity letterTo) {
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

