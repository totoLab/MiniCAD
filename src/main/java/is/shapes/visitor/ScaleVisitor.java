package is.shapes.visitor;

import is.shapes.model.*;

public class ScaleVisitor implements GraphicObjectVisitor {

    private double factor;

    public void setFactor(double factor) {
        this.factor = factor;
    }

    @Override
    public double visit(CircleObject circle) {
        circle.scale(factor);
        return 0;
    }

    @Override
    public double visit(RectangleObject rectangle) {
        rectangle.scale(factor);
        return 0;
    }

    @Override
    public double visit(ImageObject image) {
        image.scale(factor);
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
