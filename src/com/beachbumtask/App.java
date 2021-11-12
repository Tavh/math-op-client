package com.beachbumtask;

import com.beachbumtask.client.MathOperationClient;

/**
 * A math client oriented to work with a math server with a unique protocol
 * @author Tav Herzlich
 * @version 1.0
 * @since 11/12/2021
 */
public class App {
    public static void main(String[] args) {
        new MathOperationClient().start(Integer.parseInt(System.getenv("PORT")));
    }
}
