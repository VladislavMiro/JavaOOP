package com.company;

final public class Treangle {

    public Point a, b, c;
    public double ab, ac, bc;

    Treangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        ab = sideLength(a, b);
        ac = sideLength(a, c);
        bc = sideLength(b, c);
    }

    @Override
    public String toString() {
        String points = "Координаты сторон треугольника:\n" + "A = " + a +
                "\nB = " + b +
                "\nC = " + c +
                '\n';
        String sides = "\nДлины сторон трекгольника:\n" + "AB = " + ab +
                "\nAC = " + ac +
                "\nBC = " + bc +
                "\n";
        return points + sides;
    }

    private double sideLength(Point m1, Point m2) {
        return Math.sqrt(Math.pow(m2.x-m1.x, 2) + Math.pow(m2.y-m1.y, 2));
    }

    public double perimeter() {
        return ab + bc + ac;
    }

    public double area() {
        double sp = perimeter()/2;
        return Math.sqrt(sp*(sp - ab)*(sp - bc)*(sp - ac));
    }

    public Point mediansIntersectionPoint() {
        double x = (a.x + b.x + c.x) / 3;
        double y = (a.y + b.y + c.y) / 3;
        return new Point(x,y);
    }

}
