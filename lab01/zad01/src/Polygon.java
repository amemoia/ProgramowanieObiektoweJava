import java.util.Arrays;
import java.util.Locale;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = new Point(points[i].getX(), points[i].getY());
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }
    public String toSvg() {
        // <polygon points="100,10 150,190 50,190" style="fill:lime;stroke:purple;stroke-width:3" />
        //String tag = "<polygon points=\"";
        //String append = "\" style=\"fill:lime;stroke:purple;stroke-width:3\" />";
        String polypoints = "";
        /*
        for (int i = 0; i < this.points.length; i++) {
            polypoints += this.points[i].getX() + "," + this.points[i].getY();
            if (i != this.points.length-1) { polypoints+= " "; }
        }
        */
        // foreach implementation
        for (Point point : points) {
            polypoints += point.getX() + "," + point.getY();
            if (point != points[points.length-1]) { polypoints+= " "; }
        }
        //return tag+polypoints+append;
        return String.format(Locale.ENGLISH, "<polygon points=\"%s\" style=\"fill:lime;stroke:purple;stroke-width:3\" />", polypoints);
    }
    public BoundingBox boundingBox() {
        double xMin = this.points[0].getX();
        double xMax = this.points[0].getX();
        double yMin = this.points[0].getY();
        double yMax = this.points[0].getY();
        for (int i = 1; i < points.length; i++) {
            xMin = Math.min(xMin, points[i].getX());
            xMax = Math.max(xMax, points[i].getX());
            yMin = Math.min(yMin, points[i].getY());
            yMax = Math.min(yMax, points[i].getY());
        }
        return new BoundingBox(xMin, yMin, xMax - xMin, yMax - yMin);
    }
}
