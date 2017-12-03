package com.github.kosbr.cli;

import com.github.kosbr.cli.registry.CommandRegistry;
import com.github.kosbr.cli.util.PrintStreamWrapper;
import com.github.kosbr.cli.util.SequenceBuilder;
import com.github.kosbr.cli.commands.greeting.GreetingHandler;
import com.github.kosbr.cli.commands.exit.ExitHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class CommandTest {

    @Test
    public void testExitCommand() {
        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addInputMarker()
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void testGreetingCommand() throws UnsupportedEncodingException {

        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("greeting", new GreetingHandler());
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("greeting --name Bob")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addInputMarker()
                .addLine("Hello, Bob")
                .addInputMarker()
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }
}
