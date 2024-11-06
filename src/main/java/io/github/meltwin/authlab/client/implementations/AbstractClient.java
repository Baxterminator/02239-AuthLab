package io.github.meltwin.authlab.client.implementations;

import io.github.meltwin.authlab.common.PrintInterface;
import org.jetbrains.annotations.NotNull;

import java.rmi.RemoteException;

public abstract class AbstractClient {

    protected final PrintInterface server;

    protected AbstractClient(@NotNull PrintInterface server_connexion) {
        server = server_connexion;
    }

    public abstract void runClient() throws RemoteException;
}
