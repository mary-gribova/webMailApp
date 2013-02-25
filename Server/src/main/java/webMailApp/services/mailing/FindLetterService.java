package webMailApp.services.mailing;

import webMailApp.dao.communication.LetterDAO;
import webMailApp.dao.communication.UserDAO;
import webMailApp.dao.dto.FolderDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 17.02.13
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class FindLetterService {
    public List<FolderDTO> getRecievedLetters(String addressName) {
        return new LetterDAO().getRecievedLetters(addressName);
    }
}
