package org.example.Server.Command;

public class UsersCommand implements Command {
    public UsersCommand() {}

    @Override
    public void execute(CommandParameters parameter) {
        parameter.curConnect().sendMsg(parameter.curConnect().getServer().getClients());
    }
}
