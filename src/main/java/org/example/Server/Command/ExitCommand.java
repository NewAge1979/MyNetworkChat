package org.example.Server.Command;

public class ExitCommand implements Command {
    private final String message;

    public ExitCommand() {
        this.message = "До новых встреч.";
    }

    public void execute(CommandParameters parameters) {
        parameters.curConnect().sendMsg(message);
    }
}
