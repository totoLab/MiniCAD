package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectFactory;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.GroupObject;

import java.util.ArrayList;
import java.util.List;

public class GroupCommand implements Command {

    private final List<Long> ids;
    private GroupObject group;
    private final List<AbstractGraphicObject> groupMembers = new ArrayList<>();

    public GroupCommand(List<Long> ids) {
        this.ids = new ArrayList<>(ids);
    }

    @Override
    public boolean doIt() {
        GraphicObjectSingleton singleton = GraphicObjectSingleton.getInstance();
        for (Long id : ids) {
            AbstractGraphicObject obj = singleton.getById(id);
            if (obj != null) {
                groupMembers.add(obj);
            }
        }

        this.group = (GroupObject) GraphicObjectFactory.createGroup(ids);
        singleton.add(group);
        System.out.printf("Created group with id %d\n", group.getId());
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectSingleton.getInstance().remove(group.getId());
        System.out.printf("Ungrouped id %d\n", group.getId());
        return true;
    }
}
