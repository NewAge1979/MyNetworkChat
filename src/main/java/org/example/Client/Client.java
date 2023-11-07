package org.example.Client;

import org.example.Logger.LogState;
import org.example.Logger.Logger;
import org.example.Logger.LoggerImpl;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final Logger logger = LoggerImpl.getInstance();
    private Socket clientSocket = null;
    private Scanner input;
    private BufferedReader inMessage;
    private PrintWriter outMessage;

    public Client(String ipAddress, int portNumber) {
        this.input = new Scanner(System.in);
        try {
            this.clientSocket = new Socket(ipAddress, portNumber);
            this.input = new Scanner(System.in);
            this.inMessage = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.outMessage = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            boolean noExit = true;
            Receiver r = new Receiver(inMessage);
            r.start();
            //Thread getServerMessage = new Thread(r);
            //getServerMessage.start();
            while (noExit) {
                String clientMessage = input.nextLine();
                outMessage.println(clientMessage);
                if (clientMessage.equalsIgnoreCase("/exit")) {
                    noExit = false;
                }
            }
            r.interrupt();
        } catch (IOException e) {
            logger.log(LogState.ERROR, e.getMessage());
        } finally {
            closeClient();
        }
    }

    private void closeClient() {
        try {
            clientSocket.close();
            input.close();
            inMessage.close();
            outMessage.close();
        } catch (IOException e) {
            logger.log(LogState.ERROR, e.getMessage());
        }
    }
}
