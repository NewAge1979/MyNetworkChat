package org.example.Server;

import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;
import org.example.Server.Command.CommandContainer;
import org.example.Server.Command.CommandName;
import org.example.Server.Command.CommandParameters;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Connection implements Runnable {
    private Server server;
    private static int clientsCount = 0;
    private Scanner inMessage;
    private PrintWriter outMessage;
    private final Logger logger = LoggerImpl.getInstance();
    private static final String COMMAND_PREFIX = "/";
    private static final String CONN_PREFIX = "User";
    private String connName;
    private String userName;
    private final CommandContainer commandContainer = new CommandContainer();

    public Connection(Socket socket, Server server) {
        try {
            this.server = server;
            this.inMessage = new Scanner(socket.getInputStream());
            this.outMessage = new PrintWriter(socket.getOutputStream());
            clientsCount++;
            connName = String.format("%s_%d", CONN_PREFIX, clientsCount);
            userName = String.format("%s_%d", CONN_PREFIX, clientsCount);
        } catch (IOException e) {
            logger.log(LogState.ERROR, e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            logger.log(LogState.INFO, String.format("A new user %s has entered the chat.", connName));
            logger.log(LogState.INFO, String.format("Users in the chat: %d", clientsCount));
            server.sendMessageAllClients(null, String.format("Приветствуем нового участника чата: %s.", connName));
            server.sendMessageAllClients(null, String.format("Клиентов в чате: %d", clientsCount));
            commandContainer.retrieveCommand("/help").execute(new CommandParameters(this, ""));
            while (true) {
                if (inMessage.hasNext()) {
                    String clientMessage = inMessage.nextLine();
                    System.out.println(clientMessage);
                    if (clientMessage.startsWith(COMMAND_PREFIX)) {
                        logger.log(LogState.INFO, String.format("%s say: %s", connName, clientMessage));
                        String[] commandParameters = clientMessage.split(" ", 2);
                        String commandIdentifier = commandParameters[0].toLowerCase();
                        String message = (commandParameters.length > 1 ? commandParameters[1] : "");
                        commandContainer.retrieveCommand(commandIdentifier).execute(new CommandParameters(this, message));
                        if (commandIdentifier.equals(CommandName.EXIT.getCommandName())) {
                            break;
                        }
                    } else {
                        server.sendMessageAllClients(this, clientMessage);
                    }
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            logger.log(LogState.ERROR, e.getMessage());
        } finally {
            this.close();
        }
    }

    public void sendMsg(String message) {
        try {
            outMessage.println(message);
            outMessage.flush();
            logger.log(LogState.INFO, message);
        } catch (Exception e) {
            logger.log(LogState.ERROR, e.getMessage());
        }
    }

    public void close() {
        server.removeClient(this);
        clientsCount--;
        logger.log(LogState.INFO, String.format("User %s has leaved the chat.", connName));
        logger.log(LogState.INFO, String.format("Users in the chat: %d", clientsCount));
        server.sendMessageAllClients(null, String.format("Участника %s покинул чат.", connName));
        server.sendMessageAllClients(null, String.format("Клиентов в чате: %d", clientsCount));
    }

    public String getConnName() {
        return connName;
    }

    public Server getServer() {
        return server;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
