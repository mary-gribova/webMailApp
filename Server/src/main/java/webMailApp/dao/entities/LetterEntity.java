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
    @GeneratedValue
    private long letterID;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity letterFrom;


    @Column(name = "letterTo")
    private String letterTo;

    @Column(name = "letterDate", nullable = false)
    private Date letterDate;

    @Column(name = "letterTheme", length = 55, nullable = true)
    private String letterTheme;


    @Column(name = "letterBody", columnDefinition = "LONGTEXT")
    @Lob
    private String letterBody;

    @ManyToOne(cascade = CascadeType.ALL)
    private FolderEntity letterFolder;

    public LetterEntity() {
    }

    public LetterEntity(AddressEntity letterFrom, String letterTo, Date letterDate) {
        this.letterFrom = letterFrom;
        this.letterTo = letterTo;
        this.letterDate = letterDate;
    }
}
