package org.example.Server.Command;

import com.sun.jdi.event.StepEvent;

public class RenameCommand implements Command {
    public RenameCommand() {}

    @Override
    public void execute(CommandParameters parameter) {
        String newName = parameter.getMessage();
        if (newName.contains(" ")) {
            parameter.getCurConnect().sendMsg("Имя пользователя не должно содержать пробелы.");
        } else {
            if (parameter.getCurConnect().getServer().getConnect(newName) == null) {
                String oldName = parameter.getCurConnect().getConnName();
                parameter.getCurConnect().setUserName(newName);
                parameter.getCurConnect().sendMsg("Имя пользователя успешно изменено.");
                parameter.getCurConnect().getServer().sendMessageAllClients(
                        parameter.getCurConnect(),
                        String.format("%s теперь %s", oldName, newName)
                );
            } else {
                parameter.getCurConnect().sendMsg("Данное имя уже занято.");
            }
        }
    }
}
