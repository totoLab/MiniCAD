package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Symbols;
import is.shapes.specificcommand.AreaCommand;

public class AreaSymbol extends Area {

    private Symbols symbol;

    public AreaSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new AreaCommand(symbol);
        handler.handle(command);
    }
}
