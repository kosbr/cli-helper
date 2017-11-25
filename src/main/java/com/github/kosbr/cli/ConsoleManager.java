package com.github.kosbr.cli;

import com.github.kosbr.cli.registry.CommandRegistry;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class ConsoleManager {

    private final PrintStream printStream;

    private final InputStream inputStream;

    private final CommandExecutor commandExecutor;

    public ConsoleManager(final PrintStream printStream, final InputStream inputStream,
                          final CommandRegistry commandRegistry) {
        this.printStream = printStream;
        this.inputStream = inputStream;
        this.commandExecutor = new CommandExecutor(commandRegistry);
    }

    /**
     * Start command line. This method blocks the thread until some
     * command returns false in handler's method {@link CommandHandler}
     */
    public void start() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        boolean waitNext = true;
        while (waitNext) {
            try {
                final String commandStr = br.readLine();
                waitNext = commandExecutor.execute(commandStr, printStream, br);
            } catch (IOException e) {
                e.printStackTrace(printStream);
            }
        }
    }
}
