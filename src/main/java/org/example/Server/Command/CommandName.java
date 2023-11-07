package org.example.Server.Command;

public enum CommandName {
    HELP("/help"),
    RENAME("/rename"),
    USERS("/users"),
    SEND_ONE("/sendone"),
    SEND_ALL("/sendall"),
    EXIT("/exit");

    private String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}