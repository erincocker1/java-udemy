package org.example.MoreShapes;
// 05/02/2025 Section 7 exercise 37
public class Rectangle {
    private double width;
    private double length;

    public Rectangle(double width, double length) {
        this.width = width < 0 ? 0 : width;
        this.length = length < 0 ? 0 : length;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getArea() {
        return width*length;
    }
}
