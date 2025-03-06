package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class Delete implements ExpressionIF {

    private long id;

    public Delete(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
    }
}
