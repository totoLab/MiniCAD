package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.specificcommand.UngroupCommand;
import is.shapes.view.GraphicObjectPanel;

public class Ungroup implements ExpressionIF {

    Long id;

    public Ungroup(Long id) {
        this.id = id;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
        Command command = new UngroupCommand(id);
        CommandHandler handler = new NaiveCommandHandler();
        handler.handle(command);
    }
}
