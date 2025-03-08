package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.specificcommand.UngroupCommand;

public class Ungroup implements ExpressionIF {

    Long id;

    public Ungroup(Long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new UngroupCommand(id);
        handler.handle(command);
    }
}
