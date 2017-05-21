package lpi.tertyshnyi.rmi.client;

public class Main {

    public static void main(String[] args) {
        ClientRMI clientRMI = new ClientRMIImpl();
        clientRMI.start();
    }

}
