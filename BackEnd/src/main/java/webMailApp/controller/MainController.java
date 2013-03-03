package webMailApp.controller;

import webMailApp.ProjectConstants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    static ServerSocket serverSocket;
    static Socket connection;


    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(ProjectConstants.SOCKET);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                connection = serverSocket.accept();
                Runnable runnable = new MyRunnable(connection, new ObjectOutputStream(connection.getOutputStream()),
                                     new ObjectInputStream(connection.getInputStream()));
                new Thread(runnable).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}