package com.github.kosbr.cli.registry;

import com.github.kosbr.cli.CommandHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * All commands should be registered here.
 */
public class CommandRegistry {

    private final Map<String, CommandHandler> handlersMap = new ConcurrentHashMap<>();

    public void registerCommand(final String name, final CommandHandler commandHandler) {
        handlersMap.put(name, commandHandler);
    }

    public CommandHandler getCommandHandler(final String commandName) {
        return handlersMap.get(commandName);
    }
}
