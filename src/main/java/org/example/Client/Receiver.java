package org.example.Client;

import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;

import java.io.BufferedReader;
import java.io.IOException;

public class Receiver extends Thread {
    private final Logger logger = LoggerImpl.getInstance();
    private final BufferedReader inMessage;

    public Receiver(BufferedReader inMessage) {
        this.inMessage = inMessage;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(inMessage.readLine());
            }
        } catch (IOException e) {
            logger.log(LogState.ERROR, e.getMessage());
        }
    }
}
