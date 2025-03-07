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
    public void interpret(String input, GraphicObjectPanel gpanel) {
        Command command = new AreaCommand(symbol);
        CommandHandler handler = new NaiveCommandHandler();
        handler.handle(command);
    }
}
