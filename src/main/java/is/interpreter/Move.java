package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
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
    public void interpret(String input, GraphicObjectPanel gpanel) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Command command = new MoveCommand(obj, new PosAdapter(pos));
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
