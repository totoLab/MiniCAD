package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.shapes.specificcommand.ListCommand;

public class ListID extends List {

    private long id;

    public ListID(long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new ListCommand(id);
        handler.handle(command);
    }
}
