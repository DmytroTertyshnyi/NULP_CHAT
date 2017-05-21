package lpi.tertyshnyi.rmi.client.commands;

import lpi.tertyshnyi.rmi.server.ServerRMI;

import java.rmi.RemoteException;

public interface Command {

    String execute(ServerRMI server, String ... args) throws RemoteException, CommandEmptyValue;

    default void validateArgument(String argument, String argumentName) throws CommandEmptyValue {
        if(argument == null || argument.trim().equals("")){
            throw new CommandEmptyValue(argumentName + " value is empty");
        }
    }
}
