package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
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
    public void interpret(String input, GraphicObjectPanel gpanel) {
        Command command = new GroupCommand(ids);
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
