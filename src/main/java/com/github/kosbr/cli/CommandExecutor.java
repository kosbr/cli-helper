package com.github.kosbr.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.kosbr.cli.util.CliUtil;
import com.github.kosbr.cli.registry.CommandRegistry;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * This object is designed for passing text command to proper
 * {@link CommandHandler} implementation.
 */
public class CommandExecutor {

    private final CommandRegistry commandRegistry;

    public CommandExecutor(final CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    /**
     * Launch the command by proper {@link CommandHandler} implementation.
     * All handlers' exceptions should be caught inside.
     * @param command
     * @param printStream
     * @return
     */
    public boolean execute(final String command, final PrintStream printStream, final BufferedReader bufferedReader) {
        final String[] splited = command.split(" ");
        if (splited.length == 0) {
            printStream.println("Command can't be parsed");
            return true;
        }
        final String commandNameStr = splited[0];

        final CommandHandler handler = commandRegistry.getCommandHandler(commandNameStr);
        if (handler == null) {
            printStream.println("Command is not found");
            return true;
        }
        final Class optionsClass = handler.getOptionsClass();

        CommandOptions options;
        try {
            options = (CommandOptions) optionsClass.getConstructor().newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("Empty constructor is not found for " + optionsClass);
        }

        try {
            new JCommander(options, CliUtil.removeFirst(splited));
        } catch (ParameterException e) {
            printStream.println(e.getMessage());
            return true;
        }

        try {
            return handler.handle(options, printStream, bufferedReader);
        } catch (Throwable e) {
            printStream.println("Command handler error");
            return true;
        }

    }
}
