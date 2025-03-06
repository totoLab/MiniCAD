package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class PosFloat implements ExpressionIF {

    private float value;

    public PosFloat(float value) {
        if (value < 0) throw new SyntaxException("Negative value");
        else this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue() + "";
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
    }
}
