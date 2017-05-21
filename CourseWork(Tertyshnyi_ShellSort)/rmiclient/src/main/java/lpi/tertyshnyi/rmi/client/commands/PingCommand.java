package lpi.tertyshnyi.rmi.client.commands;

import lpi.tertyshnyi.rmi.server.ServerRMI;

import java.rmi.RemoteException;

public class PingCommand implements Command{

    @Override
    public String execute(ServerRMI server, String... args) throws RemoteException {
        return server.ping();
    }

}
