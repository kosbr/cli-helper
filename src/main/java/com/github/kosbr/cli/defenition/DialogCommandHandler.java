package com.github.kosbr.cli.defenition;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Handler of the command which talks to a user. The difference from
 * the {@link CommandHandler} is an additional parameter bufferedReader.
 * @param <OPTIONS>
 */
public interface DialogCommandHandler<OPTIONS extends CommandOptions> extends CommandHandler<OPTIONS> {

    /**
     * The implementation of this method performs commands handling if it
     * talks to a user.
     * @param commandOptions options from command line
     * @param printStream stream for output
     * @param bufferedReader input reader
     * @return
     */
    @Override
    boolean handle(final OPTIONS commandOptions, final PrintStream printStream, final BufferedReader bufferedReader);

    /**
     * This method is default mock due to compatibility purposes. Actually it is not
     * used and shouldn't be called.
     * @param commandOptions options from command line
     * @param printStream stream for output
     * @return
     */
    @Override
    default boolean handle(final OPTIONS commandOptions, final PrintStream printStream) {
        throw new UnsupportedOperationException("The method handle(OPTIONS commandOptions, PrintStream printStream) "
                + "of com.github.kosbr.cli.defenition.DialogCommandHandler shouldn't be called ");
    }
}
