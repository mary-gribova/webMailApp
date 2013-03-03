package webMailApp.gui.mailBox;

/**
 * @author Mariia Gribova
 * @version 1.0
 */

public class LetterEntryView {
    private String fromLetter;
    private String themeLetter;
    private String dateLetter;

    public LetterEntryView(String fromLetter, String themeLetter, String dateLetter) {
       this.dateLetter = dateLetter;
       this.fromLetter = fromLetter;
       this.themeLetter = themeLetter;
    }

     public String getFromLetter() {
        return fromLetter;
    }

    public String getThemeLetter() {
        return themeLetter;
    }

    public String getDateLetter() {
        return dateLetter;
    }
}
