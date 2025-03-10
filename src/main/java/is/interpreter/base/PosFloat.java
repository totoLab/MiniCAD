package is.interpreter.base;

import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.SyntaxException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PosFloat posFloat = (PosFloat) o;
        return Float.compare(value, posFloat.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
