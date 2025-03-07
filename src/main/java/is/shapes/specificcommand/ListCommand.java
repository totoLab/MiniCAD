package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListCommand implements Command {

    private final List<AbstractGraphicObject> objects;

    public ListCommand(AbstractGraphicObject go) {
        this.objects = new ArrayList<>();
        this.objects.add(go);
    }

    public ListCommand(List<AbstractGraphicObject> go) {
        this.objects = new ArrayList<>(go);
    }

    @Override
    public boolean doIt() {
        if (objects.size() == 1) {
            System.out.println(objects.getFirst().toString());
        } else {
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
