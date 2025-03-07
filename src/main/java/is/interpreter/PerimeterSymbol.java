package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.specificcommand.AreaCommand;
import is.shapes.specificcommand.PerimeterCommand;
import is.shapes.view.GraphicObjectPanel;

public class PerimeterSymbol extends Perimeter {

    private Symbols symbol;

    public PerimeterSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
        Command command = new PerimeterCommand(symbol);
        CommandHandler handler = new NaiveCommandHandler();
        handler.handle(command);
    }
}
