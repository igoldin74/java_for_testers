package com.myfirstprogram.test;

public class MyFirstProgram {

    public static void main(String[] args) {
        System.out.println("Hello, Java Programming For Testers");


        Point p1 = new Point(10.5, 15.6);
        Point p2 = new Point(20.6, 31.2);

        System.out.println("The distance between Point P1 with coordinates: X=" + p1.x + ", Y=" + p1.y +
                " and Point P2 with coordinates: X=" + p2.x + ", Y=" + p2.y + ", equals to " + distance(p1, p2));
    }
        //System.out.println("The distance between Point P1 with coordinates: X=" + p1.x + ", Y=" +p1.y+
        //        " and Point P2 with coordinates: X=" +p2.x+ ", Y=" +p2.y+ ", equals to " +p.distance());
        // If I'd use distance() method in my object class, how do I define it here?

    public static double distance(Point p1, Point p2) {

        {
            int i = 2;
            double x = p1.x - p2.x;
            double y = p1.y - p2.y;
            double square_x = Math.pow(x, i);
            double square_y = Math.pow(y, i);
            double sum = square_x + square_y;

            return Math.sqrt(sum);
        }

    }
}

