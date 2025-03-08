package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.RemoveCommand;
import is.shapes.view.GraphicObjectPanel;

public class Delete implements ExpressionIF {

    private long id;

    public Delete(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        GraphicObjectPanel gpanel = GraphicObjectSingleton.getInstance().getPanel();
        Command command = new RemoveCommand(gpanel, id);
        handler.handle(command);
    }
}
