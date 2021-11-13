package com.beachbumtask.client;

import java.io.*;

import static com.beachbumtask.constants.ProtocolConstants.INFO_INDICATOR;
import static com.beachbumtask.constants.ProtocolConstants.RESPONSE_END;

public class LoggingThread extends Thread {

    private final BufferedReader serverInput;
    private BufferedWriter writer;


    public LoggingThread (BufferedReader serverInput) {
        this.serverInput = serverInput;
        File logsDir = new File("logs");
        logsDir.mkdir();
        try {
            File logsFile = new File(logsDir + "/logs.txt");
            logsFile.createNewFile();
            writer = new BufferedWriter(new FileWriter(logsFile));
        } catch (IOException e) {
            System.out.println("Buffered writer failed to initialize");
        }
    }

    @Override
    public void run() {
        eventLoop();
    }

    private void eventLoop() {
        handleResponse();
    }

    private void handleResponse() {
        String line;
        try {
            while(!(line = serverInput.readLine()).equals(RESPONSE_END)) {
                if (line.contains(INFO_INDICATOR)) {
                    writer.write(line);
                    writer.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
