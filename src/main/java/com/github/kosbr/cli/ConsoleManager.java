package com.github.kosbr.cli;

import com.github.kosbr.cli.registry.CommandRegistry;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

    public void start() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        boolean waitNext = true;
        while (waitNext) {
            try {
                final String commandStr = br.readLine();
                waitNext = commandExecutor.execute(commandStr, printStream);
            } catch (IOException e) {
                e.printStackTrace(printStream);
            }
        }
    }
}
