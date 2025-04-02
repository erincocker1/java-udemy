package org.example.section21.shoes;

public record Order(int id, String shoeType, int quantity) {
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shoeType='" + shoeType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
