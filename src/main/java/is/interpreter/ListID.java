package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.ListCommand;
import is.shapes.view.GraphicObjectPanel;

public class ListID extends List {

    private long id;

    public ListID(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new ListCommand(id);
        handler.handle(command);
    }
}
