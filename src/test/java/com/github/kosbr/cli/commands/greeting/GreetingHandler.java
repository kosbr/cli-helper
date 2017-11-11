package com.github.kosbr.cli.commands.greeting;

import com.github.kosbr.cli.CommandHandler;

import java.io.PrintStream;

public class GreetingHandler implements CommandHandler<GreetingOptions> {
    @Override
    public boolean handle(GreetingOptions commandOptions, PrintStream printStream) {
        final String name = commandOptions.getName();
        printStream.println("Hello, " + name);
        return true;
    }

    @Override
    public GreetingOptions createOptions() {
        return new GreetingOptions();
    }
}
