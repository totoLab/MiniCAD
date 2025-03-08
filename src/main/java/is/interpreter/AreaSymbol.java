package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.specificcommand.AreaCommand;
import is.shapes.specificcommand.ListCommand;
import is.shapes.view.GraphicObjectPanel;

public class AreaSymbol extends Area {

    private Symbols symbol;

    public AreaSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new AreaCommand(symbol);
        handler.handle(command);
    }
}
