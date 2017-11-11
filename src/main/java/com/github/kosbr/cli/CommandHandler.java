package com.github.kosbr.cli;

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

    Class<OPTIONS> getOptionsClass();
}
