package webMailApp.dao;

import webMailApp.ProjectConstants;
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

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            try {
                UserDTO userDTO = new UserDTO(userFirstName, userLastName, userPass,
                        userBirthDate, userPhone, userAddress);

                oos.writeObject(ProjectConstants.REGISTER_USER_REQUEST);
                oos.writeObject(userDTO);

            } catch (IOException e) {
                e.printStackTrace();
                return false;
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
        }

        return true;
    }

    public boolean sendLetter(String letterFrom, String letterTo,
                           String letterTheme, Date letterDate, String letterBody) {

        boolean retState = false;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                LetterDTO letterDTO = new LetterDTO(letterFrom, letterTo,
                        letterTheme, letterDate, letterBody);

                oos.writeObject(ProjectConstants.SEND_LETTER_REQUEST);
                oos.writeObject(letterDTO);

                String s = (String) ois.readObject();

                if (s.equals("NO")) {
                    retState  = false;
                } else if (s.equals("OK")) {
                    retState = true;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return false;
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

       return retState;
    }

    public String login(String userAddress, String password) {
        String sessionID = null;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                LoginDTO login = new LoginDTO(password, userAddress);

                oos.writeObject(ProjectConstants.LOGIN_REQUEST);
                oos.writeObject(login);

                String sessionIDString = (String) ois.readObject();

                if (!sessionIDString.equals(""))
                    sessionID = sessionIDString;
                else
                    sessionID = null;

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

        return sessionID;
    }

    public UserDTO getUserBySessionID(String sessionID) {
        UserDTO user = null;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                String sessionNum = sessionID.toString();

                oos.writeObject(ProjectConstants.GET_USER_BY_SESSION_ID_REQUEST);
                oos.writeObject(sessionNum);

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

    public List<FolderDTO> getRecievedLetters(String addressName) {
        List<FolderDTO> folders = null;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                oos.writeObject(ProjectConstants.GET_RECIEVED_LETTERS);
                oos.writeObject(addressName);
                folders = new ArrayList<FolderDTO>();

                Object ob = ois.readObject();

                while (!ob.toString().equals("end")) {
                    if (ob instanceof FolderDTO)
                        folders.add((FolderDTO) ob);

                    ob = ois.readObject();
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

        return folders;
    }

    public boolean delLetters(List<LetterDTO> letters) {
        boolean retState = false;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                oos.writeObject(ProjectConstants.DEL_LETTERS_REQUEST);

                for (LetterDTO l : letters) {
                    oos.writeObject(l);
                }

                oos.writeObject("End");

                if (ois.readObject().toString().equals("OK"))
                    retState = true;
                else retState = false;

            } catch (IOException e) {
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
        } finally {
            return retState;
        }
    }

}
