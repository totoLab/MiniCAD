package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class Rectangle extends Shape {

    Pos dimensions;

    public Rectangle(Pos pos) {
        this.dimensions = pos;
    }


    public Pos getDimensions() {
        return dimensions;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {

    }
}
