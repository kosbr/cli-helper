package com.github.kosbr.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.github.kosbr.cli.util.CliUtil;
import com.github.kosbr.cli.registry.CommandRegistry;

import java.io.PrintStream;

public class CommandExecutor {

    private final CommandRegistry commandRegistry;

    public CommandExecutor(final CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public boolean execute(final String command, final PrintStream printStream) {
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
        final CommandOptions options = handler.createOptions();
        try {
            new JCommander(options, CliUtil.removeFirst(splited));
        } catch (ParameterException e) {
            printStream.println(e.getMessage());
            return true;
        }
        try {
            return handler.handle(options, printStream);
        } catch (Throwable e) {
            printStream.println("Command handler error");
            return true;
        }

    }
}
