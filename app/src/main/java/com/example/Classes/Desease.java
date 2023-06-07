package com.example.Classes;

public class Desease {
    String name;
    String symptomes;
    public Desease(String name, String symptomes) {
        this.name = name;
        this.symptomes = symptomes;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSymptomes() {
        return symptomes;
    }

    public void setSymptomes(String symptomes) {
        this.symptomes = symptomes;
    }


}
