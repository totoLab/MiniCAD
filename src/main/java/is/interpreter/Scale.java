package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
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
    public void interpret(String input, GraphicObjectPanel gpanel) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Command command = new ScaleCommand(obj, scale);
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
