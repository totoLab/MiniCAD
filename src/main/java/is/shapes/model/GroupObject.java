package is.shapes.model;

import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GroupObject extends AbstractGraphicObject implements Iterable<AbstractGraphicObject> {

    List<AbstractGraphicObject> objects = new LinkedList<>();

    public GroupObject(List<Long> ids) {
        ids.forEach(id -> objects.add(
                GraphicObjectSingleton.getInstance().getById(id)
        ));
    }

    @Override
    public void moveTo(Point2D p) {
        objects.forEach(o -> o.moveTo(p));
    }

    @Override
    public Point2D getPosition() {
        return null;
    }

    @Override
    public Dimension2D getDimension() {
        return null;
    }

    @Override
    public void scale(double factor) {
        objects.forEach(o -> o.scale(factor));
    }

    @Override
    public boolean contains(Point2D p) {
        return objects.stream().anyMatch(o -> o.contains(p));
    }

    @Override
    public String getType() {
        return "group";
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getId());
        result.append(": [");
        for (Iterator<AbstractGraphicObject> it = objects.iterator(); it.hasNext(); ) {
            AbstractGraphicObject o = it.next();
            result.append(o.getId());
            if (it.hasNext()) result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<AbstractGraphicObject> iterator() {
        return objects.iterator();
    }
}
