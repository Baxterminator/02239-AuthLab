package io.github.meltwin.authlab.common;

import org.jetbrains.annotations.NotNull;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Printer methods description for RMI
 */
public interface PrintInterface extends Remote {

    /*
     ------------------------------------------------------------------------
                                    JOB MANAGEMENT
     ------------------------------------------------------------------------
     */

    /**
     * Append the given file in the printer queue
     * @param filename the file to print
     * @param printer the printer to use for this job
     * @throws RemoteException
     */
    void print(@NotNull String filename, @NotNull String printer) throws RemoteException;

    /**
     * Fetch the queue for the given printer
     * @param printer the printer of interest
     * @return the queue in String format
     * @throws RemoteException
     */
    String[] queue(@NotNull String printer) throws RemoteException;

    /**
     * Place the given job at the top of the printer' queue
     * @param printer the printer to modify
     * @param job the job to move
     * @throws RemoteException
     */
    void topQueue(@NotNull String printer, int job) throws RemoteException;

    /*
     ------------------------------------------------------------------------
                             PRINTER SERVER MANAGEMENT
     ------------------------------------------------------------------------
     */

    /**
     * Start the printer server
     * @throws RemoteException
     */
    void start() throws RemoteException;

    /**
     * Stop the printer server
     * @throws RemoteException
     */
    void stop() throws RemoteException;

    /**
     * Restart the server. Also clear the print queue.
     * @throws RemoteException
     */
    void restart() throws RemoteException;

    /*
     ------------------------------------------------------------------------
                         PRINTER SERVER CONFIGURATION
     ------------------------------------------------------------------------
     */

    /**
     * Return the printer status as a list of strings.
     * @param printer the printer of interest
     * @return a list of lines to print to the user interface
     * @throws RemoteException
     */
    String[] status(String printer) throws RemoteException;

    /**
     * Fetch the configuration parameter value from the print server
     * @param parameter the parameter name
     * @return the parameter value
     * @throws RemoteException
     */
    String readConfig(@NotNull String parameter) throws RemoteException;

    /**
     * Set a configuration parameter value on the server
     * @param parameter the parameter name
     * @param value the value to set
     * @throws RemoteException
     */
    void setConfig(@NotNull String parameter, @NotNull String value) throws RemoteException;
}
