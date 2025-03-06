package org.example.section15.collectionschallenge;

public record Product(String sku, String name, String manufacturer, Category category) {
    @Override
    public String toString() {
        return name;
    }
}
