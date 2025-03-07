package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectFactory;
import is.shapes.model.GraphicObjectSingleton;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements Command {

    List<Long> ids;

    public GroupCommand(List<Long> ids) {
        this.ids = new ArrayList<>(ids);
    }

    @Override
    public boolean doIt() {
        AbstractGraphicObject group = GraphicObjectFactory.createGroup(ids);

        GraphicObjectSingleton.getInstance().add(group);
        System.out.printf("Created group with id %d\n", group.getId());
        return true;
    }

    @Override
    public boolean undoIt() {
        return true;
    }
}
