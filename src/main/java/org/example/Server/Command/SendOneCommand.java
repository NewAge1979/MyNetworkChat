package org.example.Server.Command;

public class SendOneCommand implements Command {
    public SendOneCommand() {}

    @Override
    public void execute(CommandParameters parameter) {
        String[] messages = parameter.message().split(" ", 2);
        if (messages.length > 1) {
            parameter.curConnect().getServer().getConnect(messages[0])
                    .sendMsg(String.format("%s ask: %s", parameter.curConnect().getUserName(), messages[1]));
        }
    }
}
