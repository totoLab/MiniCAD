package is.interpreter.base;

import is.command.CommandHandler;

public class Circle extends Shape {

    PosFloat radius;

    public Circle(PosFloat x) {
        this.radius = new PosFloat(x.getValue());
    }

    @Override
    public void interpret(String input, CommandHandler handler) {

    }

    public PosFloat getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Circle (%f)", radius.getValue());
    }
}
