package com.example.geektrust.entity;

public class MetroCard {
    private String id;
    private int blanceRem;


    public MetroCard(String id, int blanceRem) {
        this.id = id;
        this.blanceRem = blanceRem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBlanceRem() {
        return blanceRem;
    }



    public void setBlanceRem(int blanceRem) {
        this.blanceRem = blanceRem;
    }
}
