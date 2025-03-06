package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class Circle extends Shape {

    PosFloat radius;

    public Circle(PosFloat x) {
        this.radius = new PosFloat(x.getValue());
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {

    }

    public PosFloat getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Circle (%f)", radius.getValue());
    }
}
