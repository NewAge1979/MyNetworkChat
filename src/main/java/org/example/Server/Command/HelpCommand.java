package org.example.Server.Command;

public class HelpCommand implements Command {
    private final String message;

    public HelpCommand() {
        this.message = """
                Добро пожаловать в чат!
                Команды:
                /help - помощь;
                /rename - сменить имя;
                /users - вывести список пользователей;
                /sendone - отправить сообщение;
                /sendall - отправить сообщение всем;
                /exit - выход.
                Для вывода справки по команде введите: /help [команда]. Например: /help /rename""";
    }

    @Override
    public void execute(CommandParameters parameter) {
        switch (parameter.message()) {
            case "/rename" -> parameter.curConnect().sendMsg("Сменить имя: /rename [новое_имя]. Например: /rename колобок");
            case "/users" -> parameter.curConnect().sendMsg("Вывести список пользователей: /users");
            case "/sendone" -> parameter.curConnect().sendMsg("Отправить сообщение пользователю: /sendone [пользователь:сообщение]. Например: /sendone колобок:привет");
            case "/sendall" -> parameter.curConnect().sendMsg("Отправить сообщение всем: /sendall [сообщение]. Например: /sendall привет всем");
            case "/exit" -> parameter.curConnect().sendMsg("Выход из чата: /exit");
            default -> parameter.curConnect().sendMsg(message);
        }
    }
}