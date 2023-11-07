package org.example.Server.Command;

public class SendAllCommand implements Command {
    public SendAllCommand(){}

    @Override
    public void execute(CommandParameters parameter) {
        parameter.getCurConnect().getServer().sendMessageAllClients(
                parameter.getCurConnect(),
                parameter.getMessage()
        );
    }
}
