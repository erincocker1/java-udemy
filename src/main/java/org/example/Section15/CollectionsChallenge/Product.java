package org.example.Section15.CollectionsChallenge;

public record Product(String sku, String name, String manufacturer, Category category) {
    @Override
    public String toString() {
        return name;
    }
}
