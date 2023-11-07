package org.example;

import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;

public class Main {
    public static void main(String[] args) {
        Configuration config = Configuration.getInstance();
        config.setSettingFile("config.txt");
        config.readConfiguration();
        Logger logger = LoggerImpl.getInstance();
        logger.log(LogState.INFO, "Program start.");
        logger.log(LogState.INFO, String.format("HostName = %s", config.getHostName()));
        logger.log(LogState.INFO, String.format("PortNumber = %d", config.getPortNumber()));
        logger.log(LogState.INFO, String.format("LogFile = %s", config.getLogFile()));
        logger.log(LogState.INFO, "Program finish.");
    }
}