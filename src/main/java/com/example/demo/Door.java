package com.example.demo;

public class Door {

    private boolean hasPrise=false;
    private boolean isSelected=false; //Selected by the player or not.
    private boolean isClosed=true;

    public Door(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}

