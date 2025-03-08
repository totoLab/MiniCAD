package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Symbols;
import is.shapes.specificcommand.PerimeterCommand;

public class PerimeterSymbol extends Perimeter {

    private Symbols symbol;

    public PerimeterSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new PerimeterCommand(symbol);
        handler.handle(command);
    }
}
