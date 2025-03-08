package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.Symbols;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCommand implements Command {

    private final List<AbstractGraphicObject> objects = new ArrayList<>();
    private final GraphicObjectSingleton singleton = GraphicObjectSingleton.getInstance();

    public ListCommand(long id) {
        this.objects.add(singleton.getById(id));
    }

    public ListCommand(Symbols symbol) {
        List<AbstractGraphicObject> objs;
        switch (symbol) {
            case ALL -> objs = singleton.getAll();
            case GROUPS -> objs = singleton.getAllGroups();
            default -> objs = singleton.getByType(symbol.name());
        }
        this.objects.addAll(objs);
    }

    @Override
    public boolean doIt() {
        if (objects.size() == 1) {
            System.out.println(objects.getFirst().toString());
        } else {
            // no need for visitor here since I don't go down the hierarchy
            StringBuilder result = new StringBuilder();
            result.append("[");
            for (Iterator<AbstractGraphicObject> it = objects.iterator(); it.hasNext(); ) {
                AbstractGraphicObject o = it.next();
                result.append(o.getId());
                if (it.hasNext()) result.append(", ");
            }
            result.append("]");
            System.out.println(result.toString());
        }
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
