package webMailApp.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Mariia Gribova
 * @version 1.0
 *
 * Letter transfer object
 */

public class LetterDTO implements Serializable {
    private String letterFrom;
    private String letterTo;
    private String letterTheme;
    private Date letterDate;
    private String letterBody;

    public LetterDTO(String letterFrom, String letterTo,
                     String letterTheme, Date letterDate, String letterBody) {
        this.letterFrom = letterFrom;
        this.letterTo = letterTo;
        this.letterTheme = letterTheme;
        this.letterDate = letterDate;
        this.letterBody = letterBody;
    }

    public void setLetterFrom(String letterFrom) {
        this.letterFrom = letterFrom;
    }

    public void setLetterTo(String letterTo) {
        this.letterTo = letterTo;
    }

    public void setLetterTheme(String letterTheme) {
        this.letterTheme = letterTheme;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public void setLetterBody(String letterBody) {
        this.letterBody = letterBody;
    }

    public String getLetterFrom() {
        return letterFrom;
    }

    public String getLetterTo() {
        return letterTo;
    }

    public String getLetterTheme() {
        return letterTheme;
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public String getLetterBody() {
        return letterBody;
    }
}

