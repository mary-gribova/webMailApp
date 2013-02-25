package webMailApp;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import webMailApp.dao.communication.UserDAO;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 25.02.13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */
public class UserDAOTest {
    UserDAO userDAO;

    @Before
    public void initUserDAO() {
       userDAO = new UserDAO();
    }

    @Test
    public void addUserTest() {
        Assert.assertTrue(userDAO.addUser("user44", "user44", new Date(), "123456", "user44", "user44"));
        Assert.assertFalse(userDAO.addUser("user44", "user44",  new Date(), "123456", "user44", "user44"));
    }

    @Test
    public void loginUserTest() {
        Assert.assertTrue(userDAO.loginUser("user44", "user44"));
        Assert.assertFalse(userDAO.loginUser("user44", "u"));
    }

    @Test
    public void getUserByEmailTest() {
        Assert.assertEquals(userDAO.getUserByEmail("user44").getUserFirstName(), "user44");
    }

}
