package com.github.kosbr.cli;

import java.io.PrintStream;

public interface CommandHandler<OPTIONS extends CommandOptions> {

    boolean handle(OPTIONS commandOptions, PrintStream printStream);

    OPTIONS createOptions();
}