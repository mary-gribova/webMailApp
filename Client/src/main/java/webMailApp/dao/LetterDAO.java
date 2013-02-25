package webMailApp.dao;

import webMailApp.ProjectConstants;
import webMailApp.Requests;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 23.02.13
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
public class LetterDAO {
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
