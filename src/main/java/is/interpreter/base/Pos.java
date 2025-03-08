package is.interpreter.base;

import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.view.GraphicObjectPanel;

public class Pos implements ExpressionIF {

    private PosFloat x, y;

    public Pos(PosFloat x, PosFloat y) {
        this.x = new PosFloat(x.getValue());
        this.y = new PosFloat(y.getValue());
    }

    public PosFloat getX() {
        return x;
    }

    public PosFloat getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
    }
}
