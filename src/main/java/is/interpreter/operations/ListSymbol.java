package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Symbols;
import is.shapes.specificcommand.ListCommand;

public class ListSymbol extends List {

    Symbols symbol;

    public ListSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new ListCommand(symbol);
        handler.handle(command);
    }
}
