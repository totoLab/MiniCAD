package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Pos;
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
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new MoveOffsetCommand(id, new PosAdapter(pos));
        handler.handle(command);
    }
}
