package is.shapes.visitor;

import is.shapes.model.CircleObject;
import is.shapes.model.GroupObject;
import is.shapes.model.ImageObject;
import is.shapes.model.RectangleObject;

public interface GraphicObjectVisitor {
    double visit(CircleObject circle);
    double visit(RectangleObject rectangle);
    double visit(ImageObject image);
    double visit(GroupObject group);
}
