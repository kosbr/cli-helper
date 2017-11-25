package com.github.kosbr.cli;

import com.github.kosbr.cli.commands.dialog.SimpleDialogCommandHandler;
import com.github.kosbr.cli.commands.exit.ExitHandler;
import com.github.kosbr.cli.registry.CommandRegistry;
import com.github.kosbr.cli.util.PrintStreamWrapper;
import com.github.kosbr.cli.util.SequenceBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class DialogCommandTest {

    @Test
    public void testGreetingCommand() throws UnsupportedEncodingException {

        final CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.registerCommand("dialog", new SimpleDialogCommandHandler());
        commandRegistry.registerCommand("exit", new ExitHandler());

        final InputStream inputStream = SequenceBuilder.create()
                .addLine("dialog")
                .addLine("kosbr")
                .addLine("26")
                .addLine("exit")
                .getAsInputStream();

        final PrintStreamWrapper printStreamWrapper = new PrintStreamWrapper();

        final ConsoleManager consoleManager = new ConsoleManager(
                printStreamWrapper.getPrintStream(), inputStream, commandRegistry
        );

        consoleManager.start();

        final String output = printStreamWrapper.getOutContent();

        final String expectedOutput = SequenceBuilder.create()
                .addLine("What is your name?")
                .addLine("What is your age?")
                .addLine("You are kosbr and your age is 26")
                .addLine("Good bye")
                .getAsString();

        Assert.assertEquals(expectedOutput, output);
    }
}
