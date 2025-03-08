package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.visitor.MoveOffsetVisitor;

import java.awt.geom.Point2D;

public class MoveOffsettCommand implements Command {

    private final AbstractGraphicObject go;
    private final Point2D offset;
    private final MoveOffsetVisitor visitor;

    public MoveOffsettCommand(GraphicObject object, Point2D offset) {
        this.go = (AbstractGraphicObject) object;
        this.offset = offset;
        this.visitor = new MoveOffsetVisitor();
    }

    @Override
    public boolean doIt() {
        visitor.applyOffset(offset);
        go.accept(visitor);
        System.out.printf("%s with id %d moved by offset (%f, %f)\n",
                go.getType(), go.getId(), offset.getX(), offset.getY());
        return true;
    }

    @Override
    public boolean undoIt() {
        Point2D inverseOffset = new Point2D.Double(-offset.getX(), -offset.getY());
        visitor.applyOffset(inverseOffset);
        go.accept(visitor);
        return true;
    }


}
