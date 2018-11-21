package com.jasson31.menuselector;

public class Restaurant {
    private String name;
    private int preference;
    private int probability;
    Restaurant(String name, int preference){
        this.name = name;
        this.preference = preference;
        this.probability = preference;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPreference() {
        return preference;
    }
    public void setPreference(int preference) {
        this.preference = preference;
    }
    public int getProbability() {
        return probability;
    }
    public void setProbability(int probability) {
        this.probability = probability;
    }
}
