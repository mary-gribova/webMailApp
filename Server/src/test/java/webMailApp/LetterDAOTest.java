package webMailApp;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import webMailApp.dao.communication.LetterDAO;
import webMailApp.dao.dto.LetterDTO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 25.02.13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class LetterDAOTest {
    LetterDAO letterDAO;

    @Before
    public void initLetterDAO() {
       letterDAO = new LetterDAO();
    }

    @Test
    public void sendLetterTest() {
        Assert.assertEquals("No such to", letterDAO.sendLetter("user", "", "", new Date(), ""));
        Assert.assertEquals("OK", letterDAO.sendLetter("user", "user", "theme", new Date(), "Body"));
    }

    @Test
    public void delLetterTest() {
        Assert.assertNotNull(letterDAO.getRecievedLetters("user"));
    }

    @Test
    public void getLettersTest() {
        Assert.assertTrue(letterDAO.delLetters(new ArrayList<LetterDTO>()));
    }
}
