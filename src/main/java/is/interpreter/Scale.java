package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class Scale implements ExpressionIF {

    private long id;
    private double scale;

    public Scale(long id, double scale) {
        this.id = id;
        this.scale = scale;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {

    }
}
