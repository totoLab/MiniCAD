package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.adapter.PosAdapter;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.MoveOffsetCommand;
import is.shapes.view.GraphicObjectPanel;

public class MoveOffset extends Move {

    public MoveOffset(long id, Pos pos) {
        super(id, pos);
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Command command = new MoveOffsetCommand(obj, new PosAdapter(pos));
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
