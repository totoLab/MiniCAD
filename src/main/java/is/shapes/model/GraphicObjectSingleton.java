package is.shapes.model;

import is.shapes.view.GraphicObjectPanel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GraphicObjectSingleton {

    private static final GraphicObjectSingleton INSTANCE = new GraphicObjectSingleton();

    private final Map<Long, AbstractGraphicObject> objects = new HashMap<>();

    private static final GraphicObjectPanel gpanel = new GraphicObjectPanel();

    private GraphicObjectSingleton() {}

    public static GraphicObjectSingleton getInstance() {
        return INSTANCE;
    }

    public void add(AbstractGraphicObject object) {
        objects.put(object.getId(), object);
    }

    public boolean remove(long id) {
        return objects.remove(id) != null;
    }

    public AbstractGraphicObject getById(long id) {
        if (!objects.containsKey(id)) throw new IllegalArgumentException("Object with id " + id + " not found");
        return objects.get(id);
    }

    public List<AbstractGraphicObject> getAll() {
        return objects.values().stream().toList();
    }

    public List<AbstractGraphicObject> getAllGroups() {
        return objects.values().stream()
                .filter(o -> o.getType().equalsIgnoreCase("group"))
                .collect(Collectors.toList());
    }

    public List<AbstractGraphicObject> getByType(String type) {
        return objects.values().stream()
                .filter(obj -> obj.getType().equalsIgnoreCase(type) ).toList();
    }

    public GraphicObjectPanel getPanel() {
        return gpanel;
    }

    public void clear() {
        objects.clear();
    }

    public int count() {
        return objects.size();
    }
}
