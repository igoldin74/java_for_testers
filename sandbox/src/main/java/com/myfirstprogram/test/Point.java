package com.myfirstprogram.test;

public class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance() {

        {
            int i = 2;
            double x = this.x - this.x;
            double y = this.y - this.y;
            double square_x = Math.pow(x, i);
            double square_y = Math.pow(y, i);
            double sum = square_x + square_y;

            return Math.sqrt(sum);
        }
    }
}


