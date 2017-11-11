package com.github.kosbr.cli.commands.error;

import com.github.kosbr.cli.CommandHandler;

import java.io.PrintStream;

public class BadCommandHandler implements CommandHandler<BadCommandOptions> {

    @Override
    public boolean handle(BadCommandOptions commandOptions, PrintStream printStream) {
        // oops
        throw new RuntimeException("Bad exception");
    }

    @Override
    public Class<BadCommandOptions> getOptionsClass() {
        return BadCommandOptions.class;
    }
}
