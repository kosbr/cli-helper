package com.github.kosbr.cli.commands.dialog;

import com.github.kosbr.cli.defenition.DialogCommandHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class SimpleDialogCommandHandler implements DialogCommandHandler<SimpleDialogCommandOptions> {

    @Override
    public Class<SimpleDialogCommandOptions> getOptionsClass() {
        return SimpleDialogCommandOptions.class;
    }

    @Override
    public boolean handle(final SimpleDialogCommandOptions commandOptions,
                          final PrintStream printStream,
                          final BufferedReader bufferedReader) {
        printStream.println("What is your name?");
        final String name = readLineNotSafe(bufferedReader);
        printStream.println("What is your age?");
        final String age = readLineNotSafe(bufferedReader);
        printStream.println("You are " + name + " and your age is " + age);
        return true;
    }

    private String readLineNotSafe(final BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
