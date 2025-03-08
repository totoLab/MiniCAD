package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.ListCommand;
import is.shapes.view.GraphicObjectPanel;
import java.util.List;

public class ListSymbol extends is.interpreter.List {

    Symbols symbol;

    public ListSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new ListCommand(symbol);
        handler.handle(command);
    }
}
