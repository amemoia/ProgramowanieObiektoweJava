public class Main {
    public static Segment findLongestSegment(Segment[] segments){
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
    public static void main(String[] args) {
        Point p = new Point();
        p.x = 5.0;
        p.y = 7.5;

        // Zadanie 1
        System.out.println("X = " + p.x + " Y = " + p.y);
        // Zadanie 2
        System.out.println(p.toSvg());
        // Zadanie 3
        Point p2 = p.translated(4, 3);
        System.out.println(p2);
        // Zadanie 4
        Segment seg = new Segment();
        seg.a = p;
        seg.b = p2;
        System.out.println(seg.distance());
        // Zadanie 5
        // W klasie Main napisz metodę statyczną,
        // która przyjmie tablicę obiektów Segment i zwróci najdłuższy segment.
        // Następnie przenieś tę metodę do klasy Segment.
        Segment[] segments = new Segment[5];
        for (int i = 1; i < 6; i++) {
            segments[i - 1] = new Segment();
            segments[i - 1].a = new Point();
            segments[i - 1].a.x = i * 1.0;
            segments[i - 1].a.y = i * 1.1;
            segments[i - 1].b = new Point();
            segments[i - 1].b.x = i * 1.5;
            segments[i - 1].b.y = i * 1.4;
        }
        System.out.println("Implementation 1 (Main):");
        Segment longestSegment = findLongestSegment(segments);
        System.out.println(longestSegment);
        System.out.println("Dist: " + longestSegment.distance());
        System.out.println("Implementation 1 (Segment):");
        Segment longestSegment2 = Segment.findLongest(segments);
        System.out.println(longestSegment2);
        System.out.println("Dist: " + longestSegment.distance());
    }
}