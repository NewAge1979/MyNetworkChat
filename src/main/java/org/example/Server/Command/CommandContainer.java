package org.example.Server.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandContainer {
    private Map<String, Command> commandMap;

    public CommandContainer() {
        commandMap = new HashMap<>();
        commandMap.put(CommandName.HELP.getCommandName(), new HelpCommand());
        commandMap.put(CommandName.EXIT.getCommandName(), new ExitCommand());
        commandMap.put(CommandName.SEND_ALL.getCommandName(), new SendAllCommand());
        commandMap.put(CommandName.RENAME.getCommandName(), new RenameCommand());
        commandMap.put(CommandName.USERS.getCommandName(), new UsersCommand());
        commandMap.put(CommandName.SEND_ONE.getCommandName(), new SendOneCommand());
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.get(commandIdentifier);
    }
}