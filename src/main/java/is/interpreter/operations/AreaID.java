package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.shapes.specificcommand.AreaCommand;
import is.shapes.view.GraphicObjectPanel;

public class AreaID extends Area {

    private long id;

    public AreaID(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new AreaCommand(id);
        handler.handle(command);
    }
}
