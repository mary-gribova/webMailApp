package webMailApp.controller;

import webMailApp.ProjectConstants;
import webMailApp.dao.dto.LetterDTO;
import webMailApp.dao.dto.UserDTO;
import webMailApp.services.mailing.SendLetterService;
import webMailApp.services.registration.RegistrationService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */

public class MainController {
    private static ServerSocket socket;

    public static void main(String[] args) {
        try {
            socket = new ServerSocket(ProjectConstants.SOCKET);
            System.out.println("Server is running!");

            while(true) {
                Socket s = socket.accept();
                System.out.println("Server: new connection accepted!!");

                ClientThread th = new ClientThread(s);
                th.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}