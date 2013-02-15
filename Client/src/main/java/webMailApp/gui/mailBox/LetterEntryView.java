package webMailApp.gui.mailBox;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 14.02.13
 * Time: 1:55
 * To change this template use File | Settings | File Templates.
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
