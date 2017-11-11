package com.github.kosbr.cli.registry;

import com.github.kosbr.cli.CommandHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistry {

    private Map<String, CommandHandler> handlersMap = new ConcurrentHashMap<>();

    public void registerCommand(String name, CommandHandler commandHandler) {
        handlersMap.put(name, commandHandler);
    }

    public CommandHandler getCommandHandler(String commandName) {
        return handlersMap.get(commandName);
    }
}
