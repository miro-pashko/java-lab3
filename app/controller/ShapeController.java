package app.controller;

import app.model.Circle;
import app.model.Rectangle;
import app.model.Shape;
import app.model.ShapeAreaComparator;
import app.model.ShapeColorComparator;
import app.model.ShapeDataGenerator;
import app.model.Triangle;
import app.view.ShapeView;

import java.util.Arrays;

/**
 * Coordinates the Shape[] model with the ShapeView: reads menu choices
 * from the view, performs the requested operation on the dataset, and
 * hands the results back to the view to display.
 */
public class ShapeController {

    private final ShapeView view;
    private final Shape[] shapes;

    public ShapeController(ShapeView view, int datasetSize, boolean randomData) {
        this.view = view;
        this.shapes = ShapeDataGenerator.generate(datasetSize, randomData);
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.printMenu();
            String choice = view.readMenuChoice();
            switch (choice) {
                case "1":
                    view.displayShapes(shapes);
                    break;
                case "2":
                    view.displayTotalArea(totalArea(shapes));
                    break;
                case "3":
                    totalAreaBySpecifiedType();
                    break;
                case "4":
                    sortByArea();
                    break;
                case "5":
                    sortByColor();
                    break;
                case "0":
                    running = false;
                    view.displayMessage("Exiting. Goodbye!");
                    break;
                default:
                    view.displayMessage("Unknown option.\n");
            }
        }
        view.close();
    }

    private double totalArea(Shape[] source) {
        double total = 0.0;
        for (Shape shape : source) {
            total += shape.calcArea();
        }
        return total;
    }

    private void totalAreaBySpecifiedType() {
        int choice = view.promptShapeTypeChoice();
        Class<? extends Shape> type;
        String typeName;
        switch (choice) {
            case 1:
                type = Rectangle.class;
                typeName = "Rectangle";
                break;
            case 2:
                type = Triangle.class;
                typeName = "Triangle";
                break;
            default:
                type = Circle.class;
                typeName = "Circle";
                break;
        }

        double total = 0.0;
        int matchCount = 0;
        for (Shape shape : shapes) {
            if (type.isInstance(shape)) {
                total += shape.calcArea();
                matchCount++;
            }
        }
        view.displayTotalAreaByType(typeName, total, matchCount);
    }

    private void sortByArea() {
        Arrays.sort(shapes, new ShapeAreaComparator());
        view.displaySortResult("increasing area");
        view.displayShapes(shapes);
    }

    private void sortByColor() {
        Arrays.sort(shapes, new ShapeColorComparator());
        view.displaySortResult("color");
        view.displayShapes(shapes);
    }
}
