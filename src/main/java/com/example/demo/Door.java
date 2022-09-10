package com.example.demo;

import java.math.BigDecimal;

public class Door {

    private boolean hasPrise;
    private boolean isSelected; //Selected by the player or not.
    private boolean isOpen;
    private BigDecimal ods;
    private String url;

    public Door() {
        this.hasPrise = false;
        this.isSelected = false;
        this.isOpen = false;
        this.ods = new BigDecimal(0);
        this.url = "https://scontent-arn2-1.xx.fbcdn.net/v/t39.30808-6/306196995_61512\n" +
                "94371552909_8794207185441192575_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=0debeb&_nc_\n" +
                "ohc=cQ9s4p8EczYAX9MlXFY&_nc_ht=scontent-arn2-1.xx&oh=00_AT92efbAomwP-L0ROewFB\n" +
                "oKTDUdPPdf6aKyF--BTqjEt2w&oe=632266FC";
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

