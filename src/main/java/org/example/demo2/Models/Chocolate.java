package org.example.demo2.Models;

public class Chocolate extends  Food {
    public enum Type {WHITE, BLACK, MILK;}
    public Type type;

    public Chocolate(int kkal, String title, Type type) {
        super(kkal, title);
        this.type = type;
    }
    @Override
    public String getDescription() {
        String typeString = "";
        switch (this.type) {
            case WHITE:
                typeString = "белый";
                break;
            case BLACK:
                typeString = "черный";
                break;
            case MILK:
                typeString = "молочный";
                break;
        }
        return String.format("Шоколад %s", typeString);
    }
}
