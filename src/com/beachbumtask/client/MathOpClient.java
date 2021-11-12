package com.beachbumtask.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import static com.beachbumtask.constants.Commands.QUIT_COMMAND;

public class MathOpClient {

    private final Scanner localInput = new Scanner(System.in);
    private boolean isRunning = true;
    private Socket socket;
    private PrintWriter serverOutput;
    private BufferedReader serverInput;


    public void start() {
        try {
            socket = new Socket("localhost", 10000);
            serverOutput = new PrintWriter(socket.getOutputStream());
            serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            isRunning = true;
            init();
            eventLoop();
        } catch (SocketException e) {
            System.out.println("Lost connection to server");
        } catch (IOException e) {
            System.out.println("Failed to initialize client");
        }
    }

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

    private void handleResponse() throws IOException{
        String line;
        while(!(line = serverInput.readLine()).equals("<END>")) {
            System.out.println(line);
        }
    }

    private void callServer(String s) {
        serverOutput.println(s + "\n\r");
        serverOutput.flush();
    }
}
