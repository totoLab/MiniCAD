package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.shapes.specificcommand.PerimeterCommand;

public class PerimeterID extends Perimeter {

    private long id;

    public PerimeterID(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new PerimeterCommand(id);
        handler.handle(command);
    }
}
