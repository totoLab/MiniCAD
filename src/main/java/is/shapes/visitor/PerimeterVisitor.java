package is.shapes.visitor;

import is.shapes.model.*;

import java.awt.geom.Dimension2D;

public class PerimeterVisitor implements GraphicObjectVisitor {

    @Override
    public double visit(CircleObject circle) {
        return 2 * Math.PI * circle.getRadius();
    }

    @Override
    public double visit(RectangleObject rectangle) {
        Dimension2D dim = rectangle.getDimension();
        return dim.getWidth() * 2 + dim.getHeight() * 2;
    }

    @Override
    public double visit(ImageObject image) {
        Dimension2D dim = image.getDimension();
        return dim.getWidth() * 2 + dim.getHeight() * 2;
    }

    @Override
    public double visit(GroupObject group) {
        double totalPerimeter = 0;
        for (AbstractGraphicObject obj : group) {
            totalPerimeter += obj.accept(this);
        }
        return totalPerimeter;
    }
}
