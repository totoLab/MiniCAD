package is.shapes.visitor;

import is.shapes.model.*;

public interface GraphicObjectVisitor {
    double visit(CircleObject circle);
    double visit(RectangleObject rectangle);
    double visit(ImageObject image);
    double visit(GroupObject group);
}
