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
public class AddressDTO implements Serializable {
    String addressName;
    List<FolderDTO> folders;
    List<LetterDTO> recievedLetters;
    List<LetterDTO> sendedLetters;

    public String getAddressName() {
        return addressName;
    }

    public List<FolderDTO> getFolders() {
        return folders;
    }

    public List<LetterDTO> getRecievedLetters() {
        return recievedLetters;
    }

    public List<LetterDTO> getSendedLetters() {
        return sendedLetters;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public void setFolders(List<FolderDTO> folders) {
        this.folders = folders;
    }

    public void setRecievedLetters(List<LetterDTO> recievedLetters) {
        this.recievedLetters = recievedLetters;
    }

    public void setSendedLetters(List<LetterDTO> sendedLetters) {
        this.sendedLetters = sendedLetters;
    }
}
