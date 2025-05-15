public class SolidFillShapeDecorator extends ShapeDecorator {
    private String color;

    public SolidFillShapeDecorator(Shape shape, String color) {
        super(shape);
        this.color = color;
    }
}
