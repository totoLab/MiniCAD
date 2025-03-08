package is.shapes.visitor;

import is.shapes.model.*;

import java.awt.geom.Dimension2D;

public class AreaVisitor implements GraphicObjectVisitor {

    @Override
    public double visit(CircleObject circle) {
        return Math.PI * Math.pow(circle.getRadius(), 2);
    }

    @Override
    public double visit(RectangleObject rectangle) {
        Dimension2D dim = rectangle.getDimension();
        return dim.getWidth() * dim.getHeight();
    }

    @Override
    public double visit(ImageObject image) {
        Dimension2D dim = image.getDimension();
        return dim.getWidth() * dim.getHeight();
    }

    @Override
    public double visit(GroupObject group) {
        double totalArea = 0;
        for (AbstractGraphicObject obj : group) {
            totalArea += obj.accept(this);
        }
        return totalArea;
    }

}
