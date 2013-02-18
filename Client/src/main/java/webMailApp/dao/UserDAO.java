package webMailApp.dao;

import webMailApp.ProjectConstants;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.LoginDTO;
import webMailApp.dao.dto.UserDTO;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class UserDAO {
    Socket socket;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;

    public UserDAO() {
        try {
            socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean addUser(String userFirstName, String userLastName, String userPass, Date userBirthDate,
                           String userPhone, String userAddress) {
        ObjectOutputStream oos = null;
        try {

            UserDTO userDTO = new UserDTO(userFirstName, userLastName, userPass,
                                        userBirthDate, userPhone, userAddress);

            OutputStream outStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outStream);

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

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    public void sendLetter(String letterFrom, String letterTo,
                           String letterTheme, Date letterDate, String letterBody) {
        ObjectOutputStream oos = null;

        try {
           LetterDTO letterDTO = new LetterDTO(letterFrom, letterTo,
                                          letterTheme, letterDate, letterBody);

            oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(ProjectConstants.SEND_LETTER_REQUEST);
            oos.writeObject(letterDTO);

        } catch (IOException e) {
            e.printStackTrace();
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

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public String login(String userAddress, String password) {
         String sessionID = null;

        try {
            LoginDTO login = new LoginDTO(password, userAddress);

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

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

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sessionID;
    }

    public UserDTO getUserBySessionID(String sessionID) {
        UserDTO user = null;

        try {
            String sessionNum = sessionID.toString();

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

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

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return user;
    }

    public List<FolderDTO> getRecievedLetters(String addressName) {
        List<FolderDTO> folders = null;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            oos.writeObject(ProjectConstants.GET_RECIEVED_LETTERS);
            oos.writeObject(addressName);
            folders = new ArrayList<FolderDTO>();

            Object ob = ois.readObject();

            while (ob instanceof FolderDTO) {
               folders.add((FolderDTO) ob);
               ob = ois.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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

            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return folders;
    }

}
