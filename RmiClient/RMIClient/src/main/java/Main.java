import lpi.server.rmi.IServer;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    private static String hostname="localhost";
    private static Integer port=152;

    public static void main(String[] args) {
        checkHostInArgs(args);
        process();
    }

    private static void checkHostInArgs(String[] args) {
        if (args.length == 1) {
            Main.hostname = args[0];
        }
    }

    private static void process() {
        try {
            Registry registry = LocateRegistry.getRegistry(hostname, port);
            IServer proxy = (IServer) registry.lookup(IServer.RMI_SERVER_NAME);
            String sessionId = proxy.login("dmitro", "myPassword");
            showText("Session id is : " + sessionId);
            String echoResult = proxy.echo("Hello from Dmitryi Tertyshnyi");
            showText(echoResult);
            proxy.ping();
            String[] users = proxy.listUsers(sessionId); // How it could be, in case if session id is unique for each user
            showText("Users are : " + Arrays.stream(users).collect(Collectors.joining()));
            proxy.exit(sessionId);
        } catch (NotBoundException e) {
            showText("Can not find remote reference with name : " + IServer.RMI_SERVER_NAME);
        } catch (AccessException e) {
            showText("Have not access when connecting to : " + IServer.RMI_SERVER_NAME);
        } catch (IServer.ArgumentException e) {
            showText("Your login or password are empty or null.");
        } catch (IServer.ServerException e) {
            showText("Server can not process my command.");
        } catch (IServer.LoginException e) {
            showText("Your login or password are incorrect.");
        } catch (RemoteException e) {
            showText("Error during communication with remote server.");
        }
    }

    private static void showText(String text){
        System.out.println(text);
    }
}
