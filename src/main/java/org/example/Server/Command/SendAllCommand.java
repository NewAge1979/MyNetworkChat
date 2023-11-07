package org.example.Server.Command;

public class SendAllCommand implements Command {
    public SendAllCommand(){}

    @Override
    public void execute(CommandParameters parameter) {
        parameter.curConnect().getServer().sendMessageAllClients(
                parameter.curConnect(),
                parameter.message()
        );
    }
}
