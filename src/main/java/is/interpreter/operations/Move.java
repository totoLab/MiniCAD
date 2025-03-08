package is.interpreter.operations;

import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.base.Pos;
import is.shapes.adapter.PosAdapter;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.view.GraphicObjectPanel;

public class Move implements ExpressionIF {

    protected long id;
    protected Pos pos;

    public Move(long id, Pos pos) {
        this.id = id;
        this.pos = new Pos(pos.getX(), pos.getY());
    }

    public long getId() {
        return id;
    }

    public Pos getPos() {
        return pos;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler handler) {
        Command command = new MoveCommand(id, new PosAdapter(pos));
        handler.handle(command);
    }
}
