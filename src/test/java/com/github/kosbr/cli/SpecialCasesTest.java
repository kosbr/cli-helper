package com.github.kosbr.cli;

import com.github.kosbr.cli.commands.error.BadCommandHandler;
import com.github.kosbr.cli.commands.exit.ExitHandler;
import com.github.kosbr.cli.commands.greeting.GreetingHandler;
import com.github.kosbr.cli.registry.CommandRegistry;
import com.github.kosbr.cli.util.PrintStreamWrapper;
import com.github.kosbr.cli.util.SequenceBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

public class SpecialCasesTest {

    @Test
    public void testCommandNotFound() {
        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("unknown")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addLine("Command is not found")
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void testCommandHandlerException() {
        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("exit", new ExitHandler());
        commandRegistry.registerCommand("bad", new BadCommandHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("bad")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addLine("Command handler error")
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void testEmptyInput() {
        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine(" ")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addLine("Command can't be parsed")
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }

    @Test
    public void testUnknownOption() {
        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("greeting", new GreetingHandler());
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("greeting --age 12")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addLine("Unknown option: --age")
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }
}
