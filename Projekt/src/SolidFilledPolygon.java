public class SolidFilledPolygon extends Polygon {
    private String color;

    public SolidFilledPolygon(String color) {
        super(points);
        this.color = color;
    }
    @Override
    public String toSvg(String param) {
        return String.format("fill=\"%s\" %s ", color, param);
    }
}
