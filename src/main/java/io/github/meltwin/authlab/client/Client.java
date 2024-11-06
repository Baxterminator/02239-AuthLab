package io.github.meltwin.authlab.client;

import io.github.meltwin.authlab.client.implementations.AbstractClient;
import io.github.meltwin.authlab.client.implementations.TerminalClient;
import io.github.meltwin.authlab.common.PrintInterface;
import io.github.meltwin.authlab.common.ServerInformation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
            PrintInterface comp = (PrintInterface) registry.lookup(ServerInformation.REGISTRY_NAME);

            System.out.printf("Connected to print server %s on %s:%d\n", ServerInformation.REGISTRY_NAME, ServerInformation.SERVER_IP, ServerInformation.SERVER_PORT);
            AbstractClient c;
            if (args.length == 0)
                c = new TerminalClient(comp);
            else
                switch (args[0]) {
                    default:
                        c = new TerminalClient(comp);
                }
            c.runClient();

            System.out.println("Closing application ...");
        } catch (Exception e) {
            System.err.println("RMI exception:");
            e.printStackTrace();
        }
    }
}
