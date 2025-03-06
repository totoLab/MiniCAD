package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
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
    public void interpret(String input, GraphicObjectPanel gpanel) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Command command = new RemoveObjectCommand(gpanel, obj);
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
