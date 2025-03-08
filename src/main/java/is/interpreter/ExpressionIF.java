package is.interpreter;

import is.command.CommandHandler;
import is.shapes.view.GraphicObjectPanel;

public interface ExpressionIF {

    void interpret(String input, GraphicObjectPanel gpanel, CommandHandler commandHandler);
}
