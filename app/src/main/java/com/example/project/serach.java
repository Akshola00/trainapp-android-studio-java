package com.example.project;

public class serach {
    String from;
    String to;
    String timefrom;
    String timeto;
    int price;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTimefrom() {
        return timefrom;
    }

    public String getTimeto() {
        return timeto;
    }

    public int getPrice() {
        return price;
    }

    public serach(String from, String to, String timefrom, String timeto) {
        this.from = from;
        this.to = to;
        this.timefrom = timefrom;
        this.timeto = timeto;
        this.price = price;
    }
}
