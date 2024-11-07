package org.example.demo2.Models;

public abstract class Food {

    private int kkal;
    private String title;

    public Food(int kkal, String title) {
        this.kkal = kkal;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getKkal() {
        return kkal;
    }

    public void setKkal(int kkal) {
        this.kkal = kkal;
    }

    @Override
    public String toString() {
        return String.format("%s: %s ккал", this.getTitle(), this.getKkal());
    }

    public String getDescription() {
        return "";
    }

}
