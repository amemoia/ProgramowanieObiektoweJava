public class Segment {
    public Point a, b;

    public double distance() {
        // return Math.sqrt( Math.pow(p2.x - p1.x, 2)  + Math.pow(p2.y - p1.y, 2) );
        return Math.hypot(b.x- a.x, b.y- a.y);
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
