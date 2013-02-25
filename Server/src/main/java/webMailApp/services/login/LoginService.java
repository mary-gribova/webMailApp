package webMailApp.services.login;

import webMailApp.dao.communication.UserDAO;
import webMailApp.dao.dto.LoginDTO;
import webMailApp.dao.dto.UserDTO;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 17.02.13
 * Time: 1:03
 * To change this template use File | Settings | File Templates.
 */
public class LoginService {
    public boolean loginUser(LoginDTO loginDTO) {
        return new UserDAO().loginUser(loginDTO.getUserAddress(), loginDTO.getUserPassword());
    }

    public UserDTO findUserByEmail(String email) {
        return new UserDAO().getUserByEmail(email);
    }
}
