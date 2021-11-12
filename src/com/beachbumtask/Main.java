package com.beachbumtask;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Main {

/*    private static final Scanner SCANNER = new Scanner(System.in);

    static boolean isRunning = false;
    static Socket socket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 10000);
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            isRunning = true;
            init();
            eventLoop();
        } catch (SocketException e) {
            System.out.println("Lost connection to server");
        } catch (IOException e) {
            System.out.println("Failed to initialize client");
        }
    }

    private static void eventLoop() throws IOException {
        while (isRunning) {
            System.out.print("Please enter your command... ");
            final String command = SCANNER.nextLine();
            callServer(command);
            if ("quit".equals(command)) {
                quit();
            } else {
                handleResponse();
            }
        }
    }

    private static void init() {
        System.out.println("Greetings, this is application provides mathematical service. ");
        System.out.println("You can use the following commands: 'add', 'subtract', 'multiply', 'divide', 'quit'");
        System.out.println("The way to use mathematical operations is as follows: <operation>: <number>, <number>: " );
    }


    private static void quit() {
        System.out.println("Good bye!");
        isRunning = false;
    }

    private static void handleResponse() throws IOException{
        String line;
        while((line = in.readLine()).equals("")) {}
        System.out.println(line);
    }

    private static void callServer(String s) {
        out.println(s + "\n\r");
        out.flush();
    }*/
}
