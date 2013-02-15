package webMailApp.dao.entities;

import javassist.bytecode.analysis.Analyzer;
import org.hibernate.annotations.Type;

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
public class LetterDTO implements Serializable {

    @Id
    @GeneratedValue
    private long letterID;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressDTO letterFrom;


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
    private FolderDTO letterFolder;

    public LetterDTO() {
    }

    public LetterDTO(AddressDTO letterFrom, String letterTo, Date letterDate) {
        this.letterFrom = letterFrom;
        this.letterTo = letterTo;
        this.letterDate = letterDate;
    }
}
