package app.model;

/**
 * Common base for every shape in the dataset: holds the shape's color and
 * declares the area calculation every concrete shape must provide.
 */
public abstract class Shape implements Drawable {

    protected final String shapeColor;

    protected Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    public String getShapeColor() {
        return shapeColor;
    }

    /** Computes this shape's area. Implemented differently by every subclass. */
    public abstract double calcArea();

    @Override
    public String toString() {
        return String.format("%s[color=%s, area=%.2f]", getClass().getSimpleName(), shapeColor, calcArea());
    }
}
