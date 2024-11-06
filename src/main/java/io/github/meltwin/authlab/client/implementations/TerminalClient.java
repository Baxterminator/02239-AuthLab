package io.github.meltwin.authlab.client.implementations;

import io.github.meltwin.authlab.common.PrintInterface;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;
import java.util.Objects;
import java.util.Scanner;

public class TerminalClient extends AbstractClient {

    public TerminalClient(@NotNull PrintInterface itf) {
        super(itf);
    }

    @Override
    public void runClient() throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        String cmd;
        String[] args;
        do {
            // Get command
            System.out.print("$ ");
            cmd = scanner.nextLine();
            args = cmd.split(" ");

            try {
                switch (args[0]) {
                    case "print":
                        sendPrint(args);
                        break;
                    case "queue":
                        getQueue(args);
                        break;
                    case "top":
                        sendTopQueue(args);
                        break;
                    case "start":
                        askStart(args);
                        break;
                    case "stop":
                        askStop(args);
                        break;
                    case "restart":
                        askRestart(args);
                        break;
                    case "status":
                        getStatus(args);
                        break;
                    case "getconf":
                        getConfig(args);
                        break;
                    case "setconf":
                        setConfig(args);
                        break;
                }
            } catch (InvalidArgumentNumber e) {
                System.out.println(e.getMessage());
            }


        } while (!Objects.equals(args[0], "exit"));
    }

    /*
     ------------------------------------------------------------------------
                            CLIENT IMPLEMENTATION
     ------------------------------------------------------------------------
     */
    void sendPrint(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 3)
            throw new InvalidArgumentNumber("print", new String[]{"file name", "printer name"});
        server.print(args[1], args[2]);
    }

    void getQueue(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 2)
            throw new InvalidArgumentNumber("queue", new String[]{"printer name"});
        for (String s : server.queue(args[1])) {
            System.out.println(s);
        }
    }

    void sendTopQueue(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 3)
            throw new InvalidArgumentNumber("top", new String[]{"printer name", "job number"});
        server.topQueue(args[1], Integer.decode(args[2]));
    }

    void askStart(@NotNull String[] args) throws RemoteException {
        server.start();
    }

    void askStop(@NotNull String[] args) throws RemoteException {
        server.stop();
    }

    void askRestart(@NotNull String[] args) throws RemoteException {
        server.restart();
    }

    void getStatus(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 2)
            throw new InvalidArgumentNumber("status", new String[]{"printer name"});
        for (String s : server.status(args[1])) {
            System.out.println(s);
        }
    }

    void getConfig(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 2)
            throw new InvalidArgumentNumber("getconf", new String[]{"param name"});
        System.out.printf("Config is: %s%n", server.readConfig(args[1]));
    }

    void setConfig(@NotNull String[] args) throws RemoteException, InvalidArgumentNumber {
        if (args.length < 3)
            throw new InvalidArgumentNumber("setconf", new String[]{"param name", "param value"});
        server.setConfig(args[1], args[2]);
    }
}
