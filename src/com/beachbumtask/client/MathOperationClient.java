package com.beachbumtask.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static com.beachbumtask.constants.ProtocolConstants.*;

/**
 * A math operation client class, an object of the class should be instantiated and then started
 * in order to connect to the server
 */
public class MathOperationClient {

    private final Scanner localInput = new Scanner(System.in);
    private boolean isRunning = true;
    private Socket socket;
    private PrintWriter serverOutput;
    private BufferedReader serverInput;

    /**
    * Starts a MathOperationClient, establishes the connection and the i/o methods with the server
    */
    public void start(int port) {
        try {
            socket = new Socket("localhost", port);
            serverOutput = new PrintWriter(socket.getOutputStream());
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            LoggingThread loggingThread = new LoggingThread(serverInput);
//            loggingThread.start();
            isRunning = true;
            init();
            eventLoop();
        } catch (SocketException e) {
            System.out.println("Lost connection to server");
        } catch (IOException e) {
            System.out.println("Failed to initialize client");
        }
    }

    /**
     * The main loop of the client, keeps getting input from the user and received responses from the server
     * NOTE: Since a command line waits for input from the user, server-responses cannot be sent until
     *       input is given, or else the input field will be overridden
     */
    private void eventLoop() throws IOException {
        while (isRunning) {
            System.out.print("Please enter your command... ");
            final String command = localInput.nextLine();
            callServer(command);
            if (QUIT_COMMAND.equals(command)) {
                quit();
            } else {
                handleResponse();
            }
        }
    }

    private void init() {
        System.out.println("Greetings, this is application provides mathematical service. ");
        System.out.println("You can use the following commands: 'add', 'subtract', 'multiply', 'divide', 'quit'");
        System.out.println("The way to use mathematical operations is as follows: <operation>: <number>, <number>: " );
    }


    private void quit() {
        System.out.println("Good bye!");
        isRunning = false;
    }

    /**
     * Handles responses from the server, waits for the indicator '<END>' to signify the response end
     */
    private void handleResponse() throws IOException{
        String line;
        while(!(line = serverInput.readLine()).equals(RESPONSE_END)) {
            if (!line.contains(INFO_INDICATOR)) {
                System.out.println(line);
            }
        }
    }

    /**
     * Sends requests to the server, there is no need for an indicator to be sent as the server
     * handles all the requests from the client ASAP
     */
    private void callServer(String s) {
        serverOutput.println(s);
        serverOutput.flush();
    }
}
