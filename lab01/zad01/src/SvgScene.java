import java.util.Arrays;
import java.util.Locale;

public class SvgScene {
    private Polygon[] polygons = new Polygon[3];
    private int index = 0;

    public void addPolygon(Polygon poly) {
        /*
        if (index >= polygons.length) {
            index = 0;
            this.polygons[index] = poly;
            index++;
        }
        else {
            this.polygons[index] = poly;
            index++;
        }
         */
        this.polygons[(index++)%3] = poly;
    }

    @Override
    public String toString() {
        return "SvgScene{" +
                "polygons=" + Arrays.toString(polygons) +
                ", index=" + index +
                '}';
    }

    public String toSvg() {
        String polyLines = "";
        for (Polygon poly : this.polygons) {
            polyLines += "\t" + poly.toSvg() + "\n";
        }
        return String.format(Locale.ENGLISH, "<svg xmlns=\"http://www.w3.org/2000/svg\">\n%s</svg>", polyLines);
    }
}
