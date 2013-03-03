package webMailApp.services.mailing;

import webMailApp.dao.LetterDAO;
import webMailApp.dto.LetterDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 19.02.13
 * Time: 21:45
 * To change this template use File | Settings | File Templates.
 */
public class DelLettersService {
    public boolean delLetters(List<LetterDTO> letters) {
        return new LetterDAO().delLetters(letters);
    }
}
