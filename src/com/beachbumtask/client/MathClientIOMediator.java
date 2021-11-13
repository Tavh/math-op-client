package com.beachbumtask.client;

/**
 * A class implementing the Mediator pattern, synchronizes the IO operations between LoggingThread and MathOperationClient
 *
 */
public class MathClientIOMediator {

    public MathClientIOMediator(MathOperationClient mathOperationClient) {
        this.mathOperationClient = mathOperationClient;
    }
    private MathOperationClient mathOperationClient;

    public void notifyMathClient() {
        mathOperationClient.proceedToNextPrompt();
    }
}
