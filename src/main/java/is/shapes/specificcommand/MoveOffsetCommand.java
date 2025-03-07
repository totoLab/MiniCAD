package is.shapes.specificcommand;

import is.command.Command;
import is.command.CommandHandler;
import is.command.NaiveCommandHandler;
import is.interpreter.Pos;
import is.interpreter.PosFloat;
import is.shapes.adapter.PosAdapter;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GroupObject;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

public class MoveOffsetCommand extends MoveCommand {

    public MoveOffsetCommand(GraphicObject go, Point2D offset) {
        super(go, offset);
    }

    @Override
    public boolean doIt() {
        if (object instanceof GroupObject) {
            Map<GraphicObject, Point2D> offsets = calculateGroupPositions(this.newPos);
            offsets.forEach((obj, pos) -> launch(obj, pos));
        } else {
            launch(object, calculateNewPosition(object, newPos));
        }
        return true;
    }

    private void launch(GraphicObject obj, Point2D pos) {
        Command command = new MoveCommand(obj, pos);
        CommandHandler commandHandler = new NaiveCommandHandler();
        commandHandler.handle(command);
    }

    private Map<GraphicObject, Point2D> calculateGroupPositions(Point2D offset) {
        GroupObject group = (GroupObject) object;
        Map<GraphicObject, Point2D> offsets = new HashMap<>();
        for (AbstractGraphicObject child : group) {
            Point2D pos = calculateNewPosition(child, offset);
            offsets.put(child, pos);
        }
        return offsets;
    }

    private Point2D calculateNewPosition(GraphicObject obj, Point2D offset) {
        return new PosAdapter(new Pos(
                new PosFloat((float) (obj.getPosition().getX() + offset.getX())),
                new PosFloat((float) (obj.getPosition().getY() + offset.getY()))
        ));
    }
}
