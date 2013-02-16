package webMailApp.controller;

import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.UserDTO;
import webMailApp.services.mailing.SendLetterService;
import webMailApp.services.registration.RegistrationService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 16.02.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class ClientThread extends Thread {

    @Override
    public void run() {

    }

    public ClientThread(Socket socket) {

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            Object ob = ois.readObject();

            if (ob instanceof UserDTO) {
                new RegistrationService().register((UserDTO) ob);
            } else if (ob instanceof LetterDTO) {
                new SendLetterService().sendLetter((LetterDTO) ob);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
