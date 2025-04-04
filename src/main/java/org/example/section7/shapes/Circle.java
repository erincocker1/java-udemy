package org.example.Shapes;
// 05/02/25 Section 7 Exercise 37
public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius < 0 ? 0 : radius;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getArea() {
        return radius*radius*Math.PI;
    }
}
