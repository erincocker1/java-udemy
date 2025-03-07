package org.example.CarpetCost;
// 04/02/25 Section 7 Exercise 34
public class Floor {
    private double width;
    private double length;

    public Floor(double width, double length) {
        this.width = width < 0 ? 0 : width;
        this.length = length < 0 ? 0 : length;
    }

    public double getArea() {
        return this.width*this.length;
    }
}
