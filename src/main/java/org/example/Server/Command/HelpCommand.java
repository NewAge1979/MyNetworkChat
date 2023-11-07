package org.example.Server.Command;

public class HelpCommand implements Command {
    private String message;

    public HelpCommand() {
        this.message = "Добро пожаловать в чат!\n"
                + "Команды:\n"
                + "/help - помощь;\n"
                + "/rename - сменить имя;\n"
                + "/users - вывести список пользователей;\n"
                + "/sendone - отправить сообщение;\n"
                + "/sendall - отправить сообщение всем;\n"
                + "/exit - выход.\n"
                + "Для вывода справки по команде введите: /help [команда]. Например: /help /rename";
    }

    @Override
    public void execute(CommandParameters parameter) {
        switch (parameter.getMessage()) {
            case "/rename" -> parameter.getCurConnect().sendMsg("Сменить имя: /rename [новое_имя]. Например: /rename колобок");
            case "/users" -> parameter.getCurConnect().sendMsg("Вывести список пользователей: /users");
            case "/sendone" -> parameter.getCurConnect().sendMsg("Отправить сообщение пользователю: /sendone [пользователь:сообщение]. Например: /sendone колобок:привет");
            case "/sendall" -> parameter.getCurConnect().sendMsg("Отправить сообщение всем: /sendall [сообщение]. Например: /sendall привет всем");
            case "/exit" -> parameter.getCurConnect().sendMsg("Выход из чата: /exit");
            default -> parameter.getCurConnect().sendMsg(message);
        }
    }
}