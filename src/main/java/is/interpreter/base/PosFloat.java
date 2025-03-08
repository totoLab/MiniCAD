package is.interpreter.base;

import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.SyntaxException;

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
    public void interpret(String input, CommandHandler handler) {
    }
}
