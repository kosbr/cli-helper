package com.github.kosbr.cli.commands.exit;

import com.github.kosbr.cli.CommandHandler;

import java.io.PrintStream;

public class ExitHandler implements CommandHandler<ExitOptions> {

    @Override
    public boolean handle(ExitOptions commandOptions, PrintStream printStream) {
        printStream.println("Good bye");
        return false;
    }

    @Override
    public ExitOptions createOptions() {
        return new ExitOptions();
    }
}
