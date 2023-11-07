package org.example.Server;

import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Logger logger = LoggerImpl.getInstance();
    private final Map<String, Connection> clients = Collections.synchronizedMap(new HashMap<>());

    public Server(int portNumber) {
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            logger.log(LogState.INFO, String.format("Server started (portNumber = %d).", portNumber));
            while (true) {
                clientSocket = serverSocket.accept();
                Connection client = new Connection(clientSocket, this);
                clients.put(client.getConnName(), client);
                new Thread(client).start();
            }
        } catch (IOException e) {
            logger.log(LogState.ERROR, e.getMessage());
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
                logger.log(LogState.INFO, "Server finished.");
                assert serverSocket != null;
                serverSocket.close();
            } catch (NullPointerException|IOException e) {
                logger.log(LogState.ERROR, e.getMessage());
            }
        }
    }

    public void sendMessageAllClients(Connection curConnect, String message) {
        String msg = String.format("%s say: %s", (curConnect == null ? "Administrator" : curConnect.getUserName()), message);
        clients.entrySet().stream().filter(x -> !x.getValue().equals(curConnect)).forEach(x -> x.getValue().sendMsg(msg));
    }

    public void removeClient(Connection client) {
        clients.remove(client.getConnName());
    }

    public String getClients() {
        StringBuilder sb = new StringBuilder("Список пользователей:");
        for (Map.Entry<String, Connection> client : clients.entrySet()) {
            sb.append("\n").append(client.getValue().getUserName());
        }
        return sb.toString();
    }

    public Connection getConnect(String userName) {
        return clients.entrySet().stream()
                .filter(x -> x.getValue().getUserName().equals(userName))
                .findFirst()
                .map(Map.Entry :: getValue)
                .orElse(null);
    }
}