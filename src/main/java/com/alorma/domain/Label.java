package com.alorma.domain;

public class Label implements Comparable<Label> {

    public String name;
    public String color;

    public Label() {
    }

    @Override
    public int compareTo(Label another) {
        return name.toLowerCase().compareTo(another.name.toLowerCase());
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
