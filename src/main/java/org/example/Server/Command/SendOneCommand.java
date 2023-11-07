package org.example.Server.Command;

public class SendOneCommand implements Command {
    public SendOneCommand() {}

    @Override
    public void execute(CommandParameters parameter) {
        String[] messages = parameter.getMessage().split(" ", 2);
        if (messages.length > 1) {
            parameter.getCurConnect().getServer().getConnect(messages[0])
                    .sendMsg(String.format("%s ask: %s", parameter.getCurConnect().getUserName(), messages[1]));
        }
    }
}
