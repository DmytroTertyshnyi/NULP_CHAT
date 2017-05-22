package lpi.tertyshnyi.rmi.server;

import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) {
        startServer();
    }

    public static void startServer(){
        ServerRMI server = new ServerRMIImpl();
        try {
            server.start();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
