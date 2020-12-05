package fr.assemblee.entities;

public class Error {
    private String raison;
    private String stack;

    public Error(String raison, String stack) {
        this.raison = raison;
        this.stack = stack;
    }
}
