package webMailApp.services.mailing;

import webMailApp.dao.communication.LetterDAO;
import webMailApp.dao.communication.UserDAO;
import webMailApp.dao.dto.LetterDTO;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 17.02.13
 * Time: 0:55
 * To change this template use File | Settings | File Templates.
 */
public class SendLetterService {
    public String sendLetter(LetterDTO letter) {
        return new LetterDAO().sendLetter(letter.getLetterFrom(), letter.getLetterTo(), letter.getLetterTheme(),
                          letter.getLetterDate(), letter.getLetterBody());
    }
}
