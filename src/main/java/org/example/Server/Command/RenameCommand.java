package org.example.Server.Command;

public class RenameCommand implements Command {
    public RenameCommand() {}

    @Override
    public void execute(CommandParameters parameter) {
        String newName = parameter.message();
        if (newName.contains(" ")) {
            parameter.curConnect().sendMsg("Имя пользователя не должно содержать пробелы.");
        } else {
            if (parameter.curConnect().getServer().getConnect(newName) == null) {
                String oldName = parameter.curConnect().getConnName();
                parameter.curConnect().setUserName(newName);
                parameter.curConnect().sendMsg("Имя пользователя успешно изменено.");
                parameter.curConnect().getServer().sendMessageAllClients(
                        parameter.curConnect(),
                        String.format("%s теперь %s", oldName, newName)
                );
            } else {
                parameter.curConnect().sendMsg("Данное имя уже занято.");
            }
        }
    }
}
