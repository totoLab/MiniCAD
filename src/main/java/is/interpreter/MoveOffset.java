package is.interpreter;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.shapes.adapter.PosAdapter;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.specificcommand.MoveCommand;
import is.shapes.view.GraphicObjectPanel;

public class MoveOffset extends Move {

    public MoveOffset(long id, Pos pos) {
        super(id, pos);
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
        AbstractGraphicObject obj = GraphicObjectSingleton.getInstance().getById(id);
        Double x = obj.getPosition().getX();
        Double y = obj.getPosition().getY();
        Pos newPos = new Pos(
                new PosFloat(
                        x.floatValue() + pos.getX().getValue()
                ),
                new PosFloat(y.floatValue() + pos.getY().getValue()
                )
        );
        Command command = new MoveCommand(obj, new PosAdapter(newPos));
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }
}
