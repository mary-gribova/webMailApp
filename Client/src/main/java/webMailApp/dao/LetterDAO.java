package webMailApp.dao;

import webMailApp.ProjectConstants;
import webMailApp.Requests;
import webMailApp.dto.FolderDTO;
import webMailApp.dto.LetterDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Mariia Gribova
 * @version 1.0
 *
 * Class for operations with letters (communication with server),
 * such as sending letters, deleting letters and getting recieved letters
 */

public class LetterDAO {
    /**
     *
     * @param letterFrom email address of sender
     * @param letterTo email address of recipient
     * @param letterTheme letter's theme
     * @param letterDate letter's sending date
     * @param letterBody letter's body
     * @return "OK" - if the letter successfully sended, "Fail" - if there is some exception,
     *  "No such to" - if there is no such recepient
     */
    public String sendLetter(String letterFrom, String letterTo,
                              String letterTheme, Date letterDate, String letterBody) {

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                LetterDTO letterDTO = new LetterDTO(letterFrom, letterTo,
                        letterTheme, letterDate, letterBody);

                oos.writeObject(Requests.SEND_LETTER_REQUEST);
                oos.writeObject(letterDTO);

                String s = (String) ois.readObject();

                return s;
            } catch (IOException e) {
                e.printStackTrace();
                return "Fail";
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return "Fail";
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

        return "Fail";
    }

    /**
     *
     * @param addressName email address of user
     * @return list of folders with list of received letters in each
     */
    public List<FolderDTO> getRecievedLetters(String addressName) {
        List<FolderDTO> folders = null;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                oos.writeObject(Requests.GET_RECIEVED_LETTERS);
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

    /**
     *
     * @param letters list of letters to delete
     * @return true if success, false if there is some exception
     */
    public boolean delLetters(List<LetterDTO> letters) {
        boolean retState = false;

        try {
            Socket socket = new Socket(ProjectConstants.SERVER_URL, ProjectConstants.SOCKET);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            try {
                oos.writeObject(Requests.DEL_LETTERS_REQUEST);

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
