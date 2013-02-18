package webMailApp.controller;

import webMailApp.ProjectConstants;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.LoginDTO;
import webMailApp.dao.dto.UserDTO;
import webMailApp.dao.entities.AddressEntity;
import webMailApp.services.login.LoginService;
import webMailApp.services.mailing.FindLetterService;
import webMailApp.services.mailing.SendLetterService;
import webMailApp.services.registration.RegistrationService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 16.02.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class ClientThread extends Thread {
    private Socket socket;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
        try {
            this.ois = new ObjectInputStream(socket.getInputStream());
            this.oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Object ob = null;

        try {
            this.ois = new ObjectInputStream(socket.getInputStream());
            this.oos = new ObjectOutputStream(socket.getOutputStream());

            ob = ois.readObject();
              /*Register new user*/
            if (ob.toString().equals(ProjectConstants.REGISTER_USER_REQUEST)) {
                ob = ois.readObject();
                if (ob instanceof UserDTO) {
                    new RegistrationService().register((UserDTO) ob);
                }
            /*Sending letter*/
            } else if (ob.toString().equals(ProjectConstants.SEND_LETTER_REQUEST)) {
                ob = ois.readObject();
                if (ob instanceof LetterDTO) {
                    new SendLetterService().sendLetter((LetterDTO) ob);
                }
            /*Login user*/
            } else if (ob.toString().equals(ProjectConstants.LOGIN_REQUEST)) {
                ob = ois.readObject();

                if (ob instanceof LoginDTO) {
                     String sesssionID = new LoginService().loginUser((LoginDTO) ob);

                    if (sesssionID != null)
                      oos.writeObject(sesssionID.toString());
                    else
                      oos.writeObject("");
                }
             /*Get user by sessionID*/
            } else if (ob.toString().equals(ProjectConstants.GET_USER_BY_SESSION_ID_REQUEST)) {
              ob = ois.readObject();

              String sessionNum = (String) ob;
              UserDTO user = new LoginService().findUserBySessionID(sessionNum);

              oos.writeObject(user);

            /*get recieved letters*/
            } else if (ob.toString().equals(ProjectConstants.GET_RECIEVED_LETTERS)) {

                ob = ois.readObject();

                List<FolderDTO> folders = new FindLetterService().getRecievedLetters(ob.toString());

                if (folders != null) {
                    Iterator folderIterator = folders.iterator();

                    while (folderIterator.hasNext()) {
                        oos.writeObject(folderIterator.next());
                    }
                }

                oos.writeObject("end");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }  finally {
           if (socket != null) {
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
}