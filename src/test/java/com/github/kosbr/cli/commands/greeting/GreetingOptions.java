package com.github.kosbr.cli.commands.greeting;

import com.beust.jcommander.Parameter;
import com.github.kosbr.cli.CommandOptions;

public class GreetingOptions implements CommandOptions {

    @Parameter(names = "--name", description = "Your name", required = true)
    private String name;

    public String getName() {
        return name;
    }
}
