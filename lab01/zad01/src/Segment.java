public class Segment {
    // public Point a, b;
    private Point a, b;

    public Point getA()        { return a; }
    public Point getB()        { return b; }
    public void  setA(Point a) { this.a = a; }
    public void  setB(Point b) { this.b = b; }

    public Segment(){}
    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "a:" + a +
                ", b:" + b +
                '}';
    }

    public double distance() {
        // return Math.sqrt( Math.pow(p2.x - p1.x, 2)  + Math.pow(p2.y - p1.y, 2) );
        return Math.hypot(b.getX()- a.getX(), b.getY()- a.getY());
    }
    public static Segment findLongest(Segment[] segments){
        double longest = 0.0;
        int longIndex = 0;
        for (int i = 0; i < 5; i++) {
            double dist = segments[i].distance();
            if (dist > longest) {
                longest = dist;
                longIndex = i;
            }
        }
        return segments[longIndex];
    }

}
