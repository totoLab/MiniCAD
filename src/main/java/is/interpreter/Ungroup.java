package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class Ungroup implements ExpressionIF {

    Long id;

    public Ungroup(Long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {

    }
}
