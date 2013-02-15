package webMailApp.services.registration;

import webMailApp.dao.communication.UserDAO;
import webMailApp.dao.dto.UserDTO;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public class RegistrationService {
    public boolean register(UserDTO user) {
        return new UserDAO().addUser(user.getUserFirstName(), user.getUserLastName(), user.getUserPass(),
                              user.getUserBirthDate(), user.getUserPhone(), user.getUserAddress());
    }
}
