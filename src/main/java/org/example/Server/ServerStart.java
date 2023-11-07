package org.example.Server;

import org.example.Configuration;
import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;

public class ServerStart {
    public static void main(String[] args) {
        Configuration config = Configuration.getInstance();
        config.setSettingFile("config.txt");
        config.readConfiguration();
        Logger logger = LoggerImpl.getInstance();
        logger.log(LogState.INFO, "Server start.");
        Server server = new Server(config.getPortNumber());
    }
}
