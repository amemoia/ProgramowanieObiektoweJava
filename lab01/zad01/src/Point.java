import java.util.Locale;

public class Point {
    // public double x, y;
    private double x, y;
    public void   setY(double y) { this.y = y; }
    public void   setX(double x) { this.x = x; }
    public double getX()         { return x; }
    public double getY()         { return y; }

    public Point() {};
    public Point(Point old) { this.x = old.getX(); this.y = old.getY(); }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // Zadanie 2
    public String toSvg() {
        return String.format(Locale.ENGLISH, "<circle r=\"5\" cx=\"%.2f\" cy=\"%.2f\" fill=\"red\" />", x, y);
    }

//    public String toString() {
//        return "X = " + x + "\nY = " + y;
//    }

    // Zadanie 3
    public void translate(double dx, double dy) {
        x = x + dx;
        y += dy;
    }
    public Point translated(double dx, double dy) {
        Point p = new Point();
        p.x = this.x + dx;
        p.y = this.y + dy;
        return p;
    }
}
