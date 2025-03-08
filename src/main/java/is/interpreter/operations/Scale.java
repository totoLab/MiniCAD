package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.ScaleCommand;
import is.shapes.view.GraphicObjectPanel;

public class Scale implements ExpressionIF {

    private long id;
    private double scale;

    public Scale(long id, double scale) {
        this.id = id;
        this.scale = scale;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new ScaleCommand(id, scale);
        handler.handle(command);
    }
}
