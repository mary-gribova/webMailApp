package webMailApp.controller;

import webMailApp.Requests;
import webMailApp.dao.dto.FolderDTO;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.LoginDTO;
import webMailApp.dao.dto.UserDTO;
import webMailApp.services.login.LoginService;
import webMailApp.services.mailing.DelLettersService;
import webMailApp.services.mailing.FindLetterService;
import webMailApp.services.mailing.SendLetterService;
import webMailApp.services.registration.RegistrationService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 22.02.13
 * Time: 1:39
 * To change this template use File | Settings | File Templates.
 */
public class MyRunnable implements Runnable {
    Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public MyRunnable(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
    }

    @Override
    public void run() {
        Object ob;
        try {
            try {
                ob = ois.readObject();

              /*Register new user*/
                if (ob.toString().equals(Requests.REGISTER_USER_REQUEST)) {
                    ob = ois.readObject();
                    boolean b = false;

                    if (ob instanceof UserDTO) {
                        b = new RegistrationService().register((UserDTO) ob);
                    }

                    if (b) {
                        oos.writeObject("OK");
                    } else oos.writeObject("Fail");

            /*Sending letter*/
                } else if (ob.toString().equals(Requests.SEND_LETTER_REQUEST)) {
                    ob = ois.readObject();

                    if (ob instanceof LetterDTO) {
                        oos.writeObject(new SendLetterService().sendLetter((LetterDTO) ob));
                    }

            /*Login user*/
                } else if (ob.toString().equals(Requests.LOGIN_REQUEST)) {
                    ob = ois.readObject();

                    if (ob instanceof LoginDTO) {
                        boolean res = new LoginService().loginUser((LoginDTO) ob);

                        if (res)
                            oos.writeObject("OK");
                        else
                            oos.writeObject("Fail");
                    }

             /*Get user by sessionID*/
                } else if (ob.toString().equals(Requests.GET_USER_BY_EMAIL)) {
                    ob = ois.readObject();

                    String email = (String) ob;
                    UserDTO user = new LoginService().findUserByEmail(email);

                    if (user != null)
                      oos.writeObject(user);
                    else oos.writeObject("Fail");

            /*get recieved letters*/
                } else if (ob.toString().equals(Requests.GET_RECIEVED_LETTERS)) {

                    ob = ois.readObject();

                    List<FolderDTO> folders = new FindLetterService().getRecievedLetters(ob.toString());

                    if (folders != null) {
                        Iterator folderIterator = folders.iterator();

                        while (folderIterator.hasNext()) {
                            oos.writeObject(folderIterator.next());
                        }
                    }

                    oos.writeObject("end");

                } else if (ob.toString().equals(Requests.DEL_LETTERS_REQUEST)) {
                    ArrayList<LetterDTO> letters = new ArrayList<LetterDTO>();

                    ob = ois.readObject();

                    while (ob instanceof LetterDTO) {
                        letters.add((LetterDTO) ob);
                        ob = ois.readObject();
                    }

                    if (new DelLettersService().delLetters(letters))
                        oos.writeObject("OK");
                    else oos.writeObject("NO");
                }

            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }  finally {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
