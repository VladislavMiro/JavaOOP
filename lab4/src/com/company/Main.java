package com.company;

import java.awt.desktop.SystemEventListener;

public class Main {

    public static void main(String[] args) {

        Point a, b, c;
        a = new Point(0.0,0.0);
        b = new Point(10.0,0.0);
        c = new Point(3.0,6.0);

        Treangle treangle = new Treangle(a, b, c);

        System.out.println(treangle);

        double per = treangle.perimeter();

        System.out.println("Периметр треугольника P = " + per + "\n");

        double area = treangle.area();

        System.out.println("Площадь треугольника S = " + area + "\n");

        Point mediansIP = treangle.mediansIntersectionPoint();

        System.out.println("координаты точки пересечения медиан:" + mediansIP);

    }
}
