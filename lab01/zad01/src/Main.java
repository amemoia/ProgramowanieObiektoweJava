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
    public static void scene() {
        Polygon p1 = new Polygon(new Point[]{
                new Point(2.0, 6.5),
                new Point(40.0, 50.5),
                new Point(80.0, 99.5)
        });
        Polygon p2 = new Polygon(new Point[]{
                new Point(32.0, 53.5),
                new Point(32.0, 50.5),
                new Point(39.0, 55.5),
        });
        Polygon p3 = new Polygon(new Point[]{
                new Point(4.0, 8.5),
                new Point(10.0, 20.0),
                new Point(25.0, 45.0)
        });
        SvgScene scene = new SvgScene();
        scene.addPolygon(p1);
        scene.addPolygon(p2);
        scene.addPolygon(p3);
        System.out.println(scene.toSvg());
    }
    public static void main(String[] args) {
        Point p = new Point(5.0, 7.5);
        //p.setX(5.0);
        //p.setY(7.5);

        // Zadanie 1
        //System.out.println("X = " + p.getX() + " Y = " + p.getY());

        // Zadanie 2
        //System.out.println(p.toSvg());

        // Zadanie 3
        p.translate(4, 3);
        Point p2 = p.translated(4, 3);
        //System.out.println(p2);

        // Zadanie 4
        Segment seg = new Segment();
        seg.setA(p);
        seg.setB(p2);
        //System.out.println(seg.distance());

        // Zadanie 5
        // W klasie Main napisz metodę statyczną,
        // która przyjmie tablicę obiektów Segment i zwróci najdłuższy segment.
        // Następnie przenieś tę metodę do klasy Segment.
        Segment[] segments = new Segment[5];
        for (int i = 1; i < 6; i++) {
            segments[i - 1] = new Segment();
            /*
            segments[i - 1].a = new Point();
            segments[i - 1].a.setX(i * 1.0);
            segments[i - 1].a.setY(i * 1.1);
            segments[i - 1].b = new Point();
            segments[i - 1].b.setX(i * 1.5);
            segments[i - 1].b.setY(i * 1.4);
            */
            segments[i - 1] = new Segment();
            segments[i - 1].setA( new Point(i * 1.0, i * 1.1) );
            segments[i - 1].setB( new Point(i * 1.5, i * 1.4) );
        }
        /*
        System.out.println("Implementation 1 (Main):");
        Segment longestSegment = findLongestSegment(segments);
        System.out.println(longestSegment);
        System.out.println("Dist: " + longestSegment.distance());
        System.out.println("Implementation 1 (Segment):");
        Segment longestSegment2 = Segment.findLongest(segments);
        System.out.println(longestSegment2);
        System.out.println("Dist: " + longestSegment.distance());
        */
        Segment s2 = new Segment( new Point(3.5, 5.3), new Point(4.2, 2.4) );

        Point[] points = {
                new Point(10.0, 50.0),
                new Point(50.0, 100.0),
                new Point(100.0, 150.0)
        };
        Polygon poly1 = new Polygon(points);
        //System.out.println(poly1.toString());
        //System.out.println(poly1.toSvg());
        scene();
    }
}