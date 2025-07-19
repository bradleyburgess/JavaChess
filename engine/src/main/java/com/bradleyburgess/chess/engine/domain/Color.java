package com.bradleyburgess.chess.engine.domain;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
