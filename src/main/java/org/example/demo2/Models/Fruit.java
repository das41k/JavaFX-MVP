package org.example.demo2.Models;

public class Fruit extends Food {
    private Boolean isFresh;

    public Fruit(int kkal, String title, Boolean isFresh) {
        super(kkal, title);
        this.isFresh = isFresh;
    }
    @Override
    public String getDescription() {
        String isFreshString = this.isFresh ? "свежий" : "не свежий";
        return String.format("Фрукт %s", isFreshString);
    }
}
