package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.RemoveObjectCommand;
import is.shapes.view.GraphicObjectPanel;

public class Delete implements ExpressionIF {

    private long id;

    public Delete(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Command command = new RemoveObjectCommand(gpanel, obj);
        handler.handle(command);
    }
}
