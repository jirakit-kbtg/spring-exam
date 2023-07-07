package com.example.exam.requestbody;

public class PositionEditBody {
    private String currentPosition;
    private String newPosition;

    public PositionEditBody(String currentPosition, String newPosition) {
        this.currentPosition = currentPosition;
        this.newPosition = newPosition;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }
}
