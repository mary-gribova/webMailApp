package webMailApp.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mariia Gribova
 * @version 1.0
 *
 * Folder transfer object
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
