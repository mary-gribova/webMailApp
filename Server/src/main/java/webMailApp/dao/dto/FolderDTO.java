package webMailApp.dao.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class FolderDTO implements Serializable {
    private String folderName;
    private List<LetterDTO> letters;

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public FolderDTO(String folderName, List<LetterDTO> letters) {
        this.folderName = folderName;
        this.letters = letters;
    }

    public List<LetterDTO> getLetters() {
        return letters;
    }

    public void setLetters(List<LetterDTO> letters) {
        this.letters = letters;
    }
}
