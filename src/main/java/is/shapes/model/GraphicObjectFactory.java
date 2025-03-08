package is.shapes.model;

import is.interpreter.base.*;

import java.awt.geom.Point2D;
import java.util.List;

public class GraphicObjectFactory {

    public static AbstractGraphicObject createGraphicObject(Shape type, Pos position) {
        float x = position.getX().getValue();
        float y = position.getY().getValue();
        Point2D p = new Point2D.Float(x, y);

        if (type instanceof Circle) {
            Circle circle = (Circle) type;
            float radius = circle.getRadius().getValue();
            return new CircleObject(p, radius);
        }
        else if (type instanceof Rectangle) {
            Rectangle rect = (Rectangle) type;
            float width = rect.getDimensions().getX().getValue();
            float height = rect.getDimensions().getY().getValue();
            return new RectangleObject(p, width, height);
        }
        else if (type instanceof Img) {
            Img img = (Img) type;
            return new ImageObject(img.getPath(), p);
        }

        throw new UnsupportedOperationException("Unsupported shape type: " + type.getClass().getName());
    }

    public static AbstractGraphicObject createGroup(List<Long> ids) {
        return new GroupObject(ids);
    }
}