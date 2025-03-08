package is.shapes.visitor;

import java.awt.geom.Point2D;
import is.shapes.model.*;

public class MoveOffsetVisitor implements GraphicObjectVisitor {

    private Point2D offset;

    public void applyOffset(Point2D offset) {
        this.offset = offset;
    }

    @Override
    public double visit(CircleObject circle) {
        Point2D currentPos = circle.getPosition();
        Point2D newPos = new Point2D.Double(
                currentPos.getX() + offset.getX(),
                currentPos.getY() + offset.getY()
        );
        circle.moveTo(newPos);
        return 0;
    }

    @Override
    public double visit(RectangleObject rectangle) {
        Point2D currentPos = rectangle.getPosition();
        Point2D newPos = new Point2D.Double(
                currentPos.getX() + offset.getX(),
                currentPos.getY() + offset.getY()
        );
        rectangle.moveTo(newPos);
        return 0;
    }

    @Override
    public double visit(ImageObject image) {
        Point2D currentPos = image.getPosition();
        Point2D newPos = new Point2D.Double(
                currentPos.getX() + offset.getX(),
                currentPos.getY() + offset.getY()
        );
        image.moveTo(newPos);
        return 0;
    }

    @Override
    public double visit(GroupObject group) {
        for (AbstractGraphicObject child : group) {
            child.accept(this);
        }
        return 0;
    }
}
