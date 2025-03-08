package is.shapes.visitor;

import is.shapes.model.*;

import java.awt.geom.Point2D;

public class MoveVisitor implements GraphicObjectVisitor {

    private Point2D position;

    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public double visit(CircleObject circle) {
        circle.moveTo(position);
        return 0;
    }

    @Override
    public double visit(RectangleObject rectangle) {
        rectangle.moveTo(position);
        return 0;
    }

    @Override
    public double visit(ImageObject image) {
        image.moveTo(position);
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
