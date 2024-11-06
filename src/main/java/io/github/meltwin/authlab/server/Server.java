package io.github.meltwin.authlab.server;

import io.github.meltwin.authlab.common.PrintInterface;
import io.github.meltwin.authlab.common.ServerInformation;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements PrintInterface {
    public static void main(String[] args) {
        try {
            PrintInterface stub = (PrintInterface) UnicastRemoteObject.exportObject(new Server(), 0);
            Registry registry = LocateRegistry.createRegistry(ServerInformation.SERVER_PORT);
            registry.rebind(ServerInformation.REGISTRY_NAME, stub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     ------------------------------------------------------------------------
                            PRINT SERVER IMPLEMENTATION
     ------------------------------------------------------------------------
     */


    @Override
    public void print(@NotNull String filename, @NotNull String printer) throws RemoteException {
        System.out.printf("Print file %s on printer %s%n", filename, printer);
    }

    @Override
    public String[] queue(@NotNull String printer) throws RemoteException {
        System.out.printf("Requesting queue of printer %s%n", printer);
        return new String[]{"1 MyJob"};
    }

    @Override
    public void topQueue(@NotNull String printer, int job) throws RemoteException {
        System.out.printf("Putting job %d at the top of the queue of printer %s%n", job, printer);
    }

    @Override
    public void start() throws RemoteException {
        System.out.println("Starting the server");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("Stopping the server");
    }

    @Override
    public void restart() throws RemoteException {
        System.out.println("Restarting the server");
    }

    @Override
    public String[] status(String printer) throws RemoteException {
        System.out.printf("Requesting printer %s status%n", printer);
        return new String[0];
    }

    @Override
    public String readConfig(@NotNull String parameter) throws RemoteException {
        System.out.printf("Requesting parameter %s value%n", parameter);
        return "";
    }

    @Override
    public void setConfig(@NotNull String parameter, @NotNull String value) throws RemoteException {
        System.out.printf("Setting parameter %s with value %s%n", parameter, value);
    }
}
