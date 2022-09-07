package com.example.demo;

public class Door {

    private boolean hasPrise;
    private boolean isSelected; //Selected by the player or not.
    private boolean isOpen;
    private double ods;

    public Door(boolean hasPrise, boolean isSelected, boolean isOpen, double ods) {
        this.hasPrise = hasPrise;
        this.isSelected = isSelected;
        this.isOpen = isOpen;
        this.ods = ods;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getOds() {
        return ods;
    }

    public void setOds(double ods) {
        this.ods = ods;
    }

    public boolean hasPrise() {
        return hasPrise;
    }

    public void setHasPrise(boolean hasPrise) {
        this.hasPrise = hasPrise;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isClosed() {
        return isOpen;
    }

    public void setClosed(boolean closed) {
        isOpen = closed;
    }
}

