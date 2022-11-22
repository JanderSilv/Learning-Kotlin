package br.com.senaicimatec.monteseucomputador.model;

public class Product {
    private final int photo;
    private final String name;
    private final String description;
    private final String price;

    public Product(int photo, String name, String description, String price){
        this.photo = photo;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
