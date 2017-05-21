package lpi.tertyshnyi.rmi.client.commands;

import lpi.tertyshnyi.rmi.server.ServerRMI;

import java.rmi.RemoteException;

public class ExitCommand implements Command {


    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException, CommandEmptyValue {
        return "Bye Bye ...";
    }
}
