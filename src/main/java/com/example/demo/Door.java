package com.example.demo;

import java.math.BigDecimal;

public class Door {

    private boolean hasPrise;
    private boolean isSelected; //Selected by the player or not.
    private boolean isOpen;
    private BigDecimal ods;

    public Door() {
        this.hasPrise = false;
        this.isSelected = false;
        this.isOpen = false;
        this.ods = new BigDecimal(1);
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public BigDecimal getOds() {
        return ods;
    }

    public void setOds(BigDecimal ods) {
        this.ods = ods;
    }
}

