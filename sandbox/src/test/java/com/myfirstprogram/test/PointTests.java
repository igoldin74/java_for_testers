package com.myfirstprogram.test;


import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

    @Test
    public void testDistance() {
        Point p1 = new Point(10, 15);
        Point p2 = new Point(20, 30);
        Assert.assertEquals(p1.distance(),p2.distance(), 18.027756377319946);
    }

}
