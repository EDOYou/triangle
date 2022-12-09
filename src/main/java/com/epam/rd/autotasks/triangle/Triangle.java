package com.epam.rd.autotasks.triangle;

import static java.lang.Math.*;

class Triangle {
    /* Instance fields */
    //Points
    private final Point a;
    private final Point b ;
    private final Point c;

    // Sides of a triangle
    private final double AB;
    private final double AC ;
    private final double BC;

    // Constructor
    public Triangle(Point a, Point b, Point c) {
        // TODO
        this.a = a;
        this.b = b;
        this.c = c;

        this.AB = (sqrt(pow(b.getX() - a.getX(), 2) + pow(b.getY() - a.getY(), 2)));
        this.AC = (sqrt(pow(c.getX() - a.getX(), 2) + pow(c.getY() - a.getY(), 2)));
        this.BC = (sqrt(pow(c.getX() - b.getX(), 2) + pow(c.getY() - b.getY(), 2)));

        // Condition on the sides
        if ((AB + AC) <= BC || (AC + BC) <= AB || (AB + BC) <= AC)
            throw new IllegalArgumentException("Triangle is degenerative according to the sides' rule!");

        // Converting to degree
        double alpha = (getAngle(AB, AC, BC));
        double betta = (getAngle(AC, BC, AB));
        double gamma = (getAngle(BC, AB, AC));
        double sumAngles = (alpha + gamma + betta);

        // Using threshold method: Simply, we will subtract sumAngles and 180.0, make absolute value, and compare if the result is smaller than some very small number.
        double epsilon = 0.000001d;

        if ( (alpha < 0 || betta < 0 || gamma < 0) || abs(sumAngles - 180.0) > epsilon )
            throw new IllegalArgumentException("Triangle is degenerative according to the angles' rule!!");

    }

    // Finds angle between two sides
    public double getAngle(double side1, double side2, double side3) {
        double angle = toRadians(acos( (pow(side1, 2) + pow(side2, 2) - pow(side3, 2)) / (2.0*side1*side2) ));

        return angle;
    }

    // Convert to radians
    public double toRadians(double rad) {
        double angle = rad * 180 / PI;

        return angle;
    }


    // Finds area of a triangle using Heron's formula T=sqrt( s(s-a)(s-b)(s-c) ), where s is semi-perimeter and a,b,c are sides
    public double area() {
        // TODO
        // semi-perimeter
        double s = (this.AB + this.AC + this.BC) / 2.0;
        double area = round( sqrt( s*(s - this.AB)*(s - this.AC)*(s - this.BC)) * 100.0) / 100.0;

        return area;
    }

    // The centroid of a triangle is the point of intersection of its medians
    public Point centroid(){
        // TODO(
        Point centroid = new Point( (a.getX() + b.getX() + c.getX())/3 , (a.getY() + b.getY() + c.getY())/3 );

        return centroid;
    }

}
