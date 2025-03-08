package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Symbols;
import is.shapes.specificcommand.AreaCommand;
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
