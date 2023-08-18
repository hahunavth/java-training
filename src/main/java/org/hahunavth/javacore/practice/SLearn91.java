package org.hahunavth.javacore.practice;

public class SLearn91 {
    public static void main(String args[]) {
        Shape s = new Shape(1, 2.1);
        Rectangle r = new Rectangle(1.2, 2.3);
        Circle c = new Circle(3.2);

        System.out.println(s);
        System.out.println(r);
        System.out.println(r.getArea());
        System.out.println(c);
        System.out.println(c.getArea());
    }
}

class Shape {
    protected double height;
    protected double width;

    public Shape(double height, double width) {
        this.height = height;
        this.width = width;
    }
}

class Rectangle extends Shape {
    public Rectangle(double height, double width) {
        super(height, width);
    }
    public double getArea() {
        return this.height * this.width;
    }

    public double getPerimeter() {
        return 2 * (this.height + this.width);
    }
}

class Circle extends Shape {
    public Circle(double r) {
        super(r, r);
    }

    public double getArea() {
        return 3.14 * this.height * 2;
    }

    public double andgetCircumference() {
        return 2 * this.height * 3.14;
    }
}