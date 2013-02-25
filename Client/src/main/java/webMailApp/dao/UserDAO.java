package webMailApp.dao;

import org.mindrot.jbcrypt.BCrypt;
import webMailApp.ProjectConstants;
import webMailApp.Requests;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.LoginDTO;
import webMailApp.dao.dto.UserDTO;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {

    public boolean addUser(String userFirstName, String userLastName, String userPass, Date userBirthDate,
                           String userPhone, String userAddress) {
        boolean retState = false;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                UserDTO userDTO = new UserDTO(userFirstName, userLastName, userPass,
                        userBirthDate, userPhone, userAddress);

                oos.writeObject(Requests.REGISTER_USER_REQUEST);
                oos.writeObject(userDTO);

                String resp = (String) ois.readObject();

                if (resp.equals("Fail")) {
                    retState = false;
                } else retState = true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {

                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return retState;
        }
    }

    public boolean login(String userAddress, String password) {
        boolean retState = false;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                LoginDTO login = new LoginDTO(password, userAddress);

                oos.writeObject(Requests.LOGIN_REQUEST);
                oos.writeObject(login);

                String resp = (String) ois.readObject();

                if (!resp.equals("Fail"))
                    retState = true;
                else
                    retState = false;

            } catch (IOException e) {
                e.printStackTrace();
                retState = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                retState = false;
            } finally {

                if (ois != null) {
                    try {
                        ois.close();
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return retState;
        }
    }

    public UserDTO getUserByEmail(String email) {
        UserDTO user = null;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                oos.writeObject(Requests.GET_USER_BY_EMAIL);
                oos.writeObject(email);

                Object ob = ois.readObject();

                if (ob instanceof UserDTO) {
                    user = (UserDTO) ob;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {

                if (ois != null) {
                    try {
                        ois.close();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

}
