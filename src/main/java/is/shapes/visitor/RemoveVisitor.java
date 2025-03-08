package is.shapes.visitor;

import is.shapes.model.*;
import is.shapes.view.GraphicObjectPanel;
import java.util.*;

public class RemoveVisitor implements GraphicObjectVisitor {
    private final GraphicObjectPanel panel;
    private final GraphicObjectSingleton singleton = GraphicObjectSingleton.getInstance();
    private final Map<Long, AbstractGraphicObject> removedObjectsMap = new HashMap<>();

    public RemoveVisitor(GraphicObjectPanel panel) {
        this.panel = panel;
    }

    public List<AbstractGraphicObject> getRemovedObjects() {
        return new ArrayList<>(removedObjectsMap.values());
    }

    @Override
    public double visit(CircleObject circle) {
        removedObjectsMap.put(circle.getId(), circle);
        singleton.remove(circle.getId());
        panel.remove(circle);
        System.out.printf("%s with id %d removed\n", circle.getType(), circle.getId());
        return 0;
    }

    @Override
    public double visit(RectangleObject rectangle) {
        removedObjectsMap.put(rectangle.getId(), rectangle);
        singleton.remove(rectangle.getId());
        panel.remove(rectangle);
        System.out.printf("%s with id %d removed\n", rectangle.getType(), rectangle.getId());
        return 0;
    }

    @Override
    public double visit(ImageObject image) {
        removedObjectsMap.put(image.getId(), image);
        singleton.remove(image.getId());
        panel.remove(image);
        System.out.printf("%s with id %d removed\n", image.getType(), image.getId());
        return 0;
    }

    @Override
    public double visit(GroupObject group) {
        removedObjectsMap.put(group.getId(), group);
        for (AbstractGraphicObject child : group) {
            child.accept(this);
        }

        singleton.remove(group.getId());
        panel.remove(group);
        System.out.printf("%s with id %d removed\n", group.getType(), group.getId());
        return 0;
    }
}