package is.interpreter;

import is.command.CommandHandler;

public interface ExpressionIF {

    void interpret(String input, CommandHandler commandHandler);
}
