package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static Configuration instance = null;
    private String settingFile;
    private String hostName;
    private int portNumber;
    private String logFile;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    public void setSettingFile(String settingFile) {
        this.settingFile = settingFile;
    }

    public void readConfiguration() {
        try (FileReader reader = new FileReader(settingFile)) {
            Properties config = new Properties();
            config.load(reader);
            this.hostName = config.getProperty("HostName");
            try {
                this.portNumber = Integer.parseInt(config.getProperty("PortNumber"));
            } catch (NumberFormatException e) {
                throw new RuntimeException();
            }
            if (config.getProperty("LogType").equalsIgnoreCase("file")) {
                logFile = config.getProperty("LogFile");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String getHostName() {
        return hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public String getLogFile() {
        return logFile;
    }
}