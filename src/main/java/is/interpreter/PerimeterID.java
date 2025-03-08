package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.specificcommand.AreaCommand;
import is.shapes.specificcommand.PerimeterCommand;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterID extends Perimeter {

    private long id;

    public PerimeterID(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new PerimeterCommand(id);
        handler.handle(command);
    }
}
