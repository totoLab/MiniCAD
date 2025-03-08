package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.shapes.specificcommand.GroupCommand;
import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;
import java.util.List;

public class Group implements ExpressionIF {

    private List<Long> ids;

    public Group(List<Long> ids) {
        this.ids = new ArrayList<>(ids);
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new GroupCommand(ids);
        handler.handle(command);
    }
}
