package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.base.Pos;
import is.shapes.adapter.PosAdapter;
import is.shapes.specificcommand.MoveOffsetCommand;

public class MoveOffset extends Move {

    public MoveOffset(long id, Pos pos) {
        super(id, pos);
    }

    @Override
    public void interpret(String input, CommandHandler handler) {
        Command command = new MoveOffsetCommand(id, new PosAdapter(pos));
        handler.handle(command);
    }
}
