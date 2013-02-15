package webMailApp.dao;

import webMailApp.ProjectConstants;
import webMailApp.dao.dto.UserDTO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {
    Socket socket;
    ObjectOutputStream oos;

    public boolean addUser(String userFirstName, String userLastName, String userPass, Date userBirthDate,
                           String userPhone, String userAddress) {

        try {
            socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            UserDTO userDTO = new UserDTO(userFirstName, userLastName, userPass,
                                        userBirthDate, userPhone, userAddress);

            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(userDTO);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if(socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }
}
