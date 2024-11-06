package io.github.meltwin.authlab.client.implementations;

import org.jetbrains.annotations.NotNull;

/**
 * Exception thrown by the TerminalClient when the given arguments are not enough
 */
public class InvalidArgumentNumber extends RuntimeException {
    public InvalidArgumentNumber(String command, String[] arguments) {
        super(InvalidArgumentNumber.makeMessage(command, arguments));
    }

    static private String makeMessage(@NotNull String command, @NotNull String[] arguments) {
        String m = "Invalid number of arguments for command %1$s.\nUsage: %1$s";
        m = String.format(m, command);

        for (String arg : arguments)
            m = String.format("%s <%s>", m, arg);

        return m;
    }
}
