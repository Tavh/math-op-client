package com.beachbumtask.client;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.beachbumtask.constants.ProtocolConstants.INFO_INDICATOR;
import static com.beachbumtask.constants.ProtocolConstants.RESPONSE_END_INDICATOR;

public class LoggingThread extends Thread {

    private final BufferedReader serverInput;
    private BufferedWriter writer;
    private final MathClientIOMediator ioMediator;

    public LoggingThread (InputStream inputStream, MathClientIOMediator ioMediator) {
        this.ioMediator = ioMediator;
        this.serverInput = new BufferedReader(new InputStreamReader(inputStream));
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

    /**
     * Handles responses from the server, waits for the indicator '<END>' to signify the response end
     */
    private void handleResponse() {
        String line;
        try {
            while(true) {
                line = serverInput.readLine();
                if (!line.equals(RESPONSE_END_INDICATOR)) {
                    if (line.contains(INFO_INDICATOR)) {
                        writer.write(
                                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss")) + " " + line + "\n"
                        );
                        writer.flush();
                    } else {
                        System.out.println(line);
                        ioMediator.notifyMathClient();
                    }
                }
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
