package is.interpreter.base;

import is.command.CommandHandler;

public class Rectangle extends Shape {

    Pos dimensions;

    public Rectangle(Pos pos) {
        this.dimensions = pos;
    }


    public Pos getDimensions() {
        return dimensions;
    }

    @Override
    public void interpret(String input, CommandHandler commandHandler) {

    }
}
