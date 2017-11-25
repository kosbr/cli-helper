package com.github.kosbr.cli;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Handler of the command.
 * @param <OPTIONS>
 */
public interface CommandHandler<OPTIONS extends CommandOptions> {

    /**
     * The implementation of this method performs commands handling.
     * @param commandOptions options from command line
     * @param printStream stream for output
     * @return if the console should wait for other commands or it should be closed.
     */
    boolean handle(OPTIONS commandOptions, PrintStream printStream);


    /**
     * The implementation of this method performs commands handling if it
     * talks to a user. It has default implementation for compatibility.
     * See {@link DialogCommandHandler} for creation dialog commands.
     * @param commandOptions options from command line
     * @param printStream stream for output
     * @param bufferedReader input reader
     * @return if the console should wait for other commands or it should be closed.
     */
    default boolean handle(final OPTIONS commandOptions, final PrintStream printStream,
                           final BufferedReader bufferedReader) {
        return this.handle(commandOptions, printStream);

    }

    /**
     * It should return class of the Options for automatic creation using reflection.
     * @return
     */
    Class<OPTIONS> getOptionsClass();
}
