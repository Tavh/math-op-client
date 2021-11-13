package com.beachbumtask.client;

public class MathClientIOMediator {

    public MathClientIOMediator(MathOperationClient mathOperationClient) {
        this.mathOperationClient = mathOperationClient;
    }
    private MathOperationClient mathOperationClient;

    public void notifyMathClient() {
        mathOperationClient.proceedToNextPrompt();
    }
}
