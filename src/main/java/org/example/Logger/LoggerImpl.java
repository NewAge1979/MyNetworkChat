package org.example.Logger;

import org.example.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerImpl implements Logger {
    private static Logger instance = null;
    private final String logFile;

    private LoggerImpl() {
        Configuration config = Configuration.getInstance();
        this.logFile = config.getLogFile();
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (LoggerImpl.class) {
                if (instance == null) {
                    instance = new LoggerImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void log(LogState state, String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(String.format("[%s] - [%s]: %s\n", LocalDateTime.now().format(formatter), state.name(), message));
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}