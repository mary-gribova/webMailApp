package webMailApp.dao;

import webMailApp.ProjectConstants;
import webMailApp.Requests;
import webMailApp.dto.LoginDTO;
import webMailApp.dto.UserDTO;

import java.io.*;
import java.net.Socket;
import java.util.*;


/**
 * @author Mariia Gribova
 * @version 1.0
 *
 * Class for operations with user information (communication with server),
 * such as registering new user, login and get user by email address
 */

public class UserDAO {
    /**
     *
     * @param userFirstName user first name
     * @param userLastName user last name
     * @param userPass user password
     * @param userBirthDate user birth date
     * @param userPhone user phone
     * @param userAddress user email address
     * @return true if success, false if there is some exception
     */
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

    /**
     *
     * @param userAddress email address of user
     * @param password user's password
     * @return true if success, false if there is some exception
     */
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

    /**
     *
     * @param email user's email address
     * @return user transfer object or null if there is no user with such email address
     */
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
