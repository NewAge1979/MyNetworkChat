package org.example.Server.Command;

import org.example.Server.Connection;

public class CommandParameters {
    private Connection curConnect;
    private String message;

    public CommandParameters(Connection curConnect, String message) {
        this.curConnect = curConnect;
        this.message = message;
    }

    public Connection getCurConnect() {
        return curConnect;
    }

    public String getMessage() {
        return message;
    }
}
