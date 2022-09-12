package com.example.demo;

import java.math.BigDecimal;

public class Door {

    private boolean hasPrice;
    private boolean isSelected; //Selected by the player or not.
    private boolean isOpen;
    private BigDecimal ods;
    private int arrayIndex;

    public Door( int arrayIndex) {
        this.hasPrice = false;
        this.isSelected = false;
        this.isOpen = false;
        this.ods = new BigDecimal(0);
        this.arrayIndex = arrayIndex;
    }

    public boolean hasPrice() {
        return hasPrice;
    }

    public void setHasPrice(boolean hasPrice) {
        this.hasPrice = hasPrice;
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

    public int getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }
}

