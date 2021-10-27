import java.util.Random;
import java.lang.Math;

class Point {

    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        System.out.println("Point [" + x + ", " + y + "] has been created.");
    }

    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }

    public void movePoint(double xAxisShift, double yAxisShift) {
        this.x += xAxisShift;
        this.y += yAxisShift;
    }
}

class Circle {
    public Point center;
    public double radius;

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public void printCoordinates() {
        System.out.println("x: " + this.center.x);
        System.out.println("y: " + this.center.y);
    }
}

class Square {
    public Point center;
    public double sideSize;
    public double rotation; // radians/pi from OX, 1 unit = 180 deg

    public Square(Circle base, double rotation) { //square inscribed in circle
        this.center = new Point(base.center);
        this.rotation = rotation % 2; // 360 deg = 0 deg
        this.sideSize = base.radius*2/Math.sqrt(2);
    }

    public Square(Point pointA, Point pointB) {
        this.center = new Point(
            (pointA.x + pointB.x)/2,
            (pointA.y + pointB.y)/2
        );
        double diagonal = Math.sqrt(
            Math.pow(Math.abs((pointA.x - pointB.x)), 2) +
            Math.pow(Math.abs((pointA.y - pointB.y)), 2)
        );
        this.sideSize = diagonal/Math.sqrt(2);
        this.rotation = Math.atan2(pointB.y - pointA.y, pointB.x - pointA.x)/Math.PI - 0.25; // diagonal - 45 deg 
        if(this.rotation < 0) this.rotation += 2;
    }

    public void printCoordinates() {
        double diagonalHalf = this.sideSize*Math.sqrt(2)/2;
        System.out.println("Ax: " + (this.center.x + diagonalHalf * Math.cos((rotation+0.25)*Math.PI)) + ", Ay: " + (this.center.y + diagonalHalf * Math.sin((rotation+0.25)*Math.PI)));
        System.out.println("Bx: " + (this.center.x + diagonalHalf * Math.cos((rotation+0.75)*Math.PI)) + ", By: " + (this.center.y + diagonalHalf * Math.sin((rotation+0.75)*Math.PI)));
        System.out.println("Cx: " + (this.center.x + diagonalHalf * Math.cos((rotation+1.25)*Math.PI)) + ", Cy: " + (this.center.y + diagonalHalf * Math.sin((rotation+1.25)*Math.PI)));
        System.out.println("Dx: " + (this.center.x + diagonalHalf * Math.cos((rotation+1.75)*Math.PI)) + ", Dy: " + (this.center.y + diagonalHalf * Math.sin((rotation+1.75)*Math.PI)));
    }
}

public class Main {

    public static void main(String[] args) {
        double inputX = 0;
        double inputY = 0;
        double inputRadius = 5;

        Point point = new Point(inputX, inputY);
        Circle circle = new Circle(point, inputRadius);

        circle.center.movePoint(Main.getRandomNumber(), Main.getRandomNumber());
        circle.printCoordinates();
        Square square1 = new Square(circle, (double) getRandomNumber());
        square1.printCoordinates();
        Square square2 = new Square(new Point(getRandomNumber(), getRandomNumber()), new Point(getRandomNumber(), getRandomNumber()));
        square2.printCoordinates();
    }

    public static double getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(10);
    }

}
