package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.GroupObject;

import java.util.ArrayList;
import java.util.List;

public class UngroupCommand implements Command {

    private final long id;
    private final GroupObject originalGroup;
    private final List<AbstractGraphicObject> groupMembers = new ArrayList<>();

    public UngroupCommand(long id) {
        this.id = id;
        this.originalGroup = (GroupObject) GraphicObjectSingleton.getInstance().getById(id);
    }

    @Override
    public boolean doIt() {
        for (AbstractGraphicObject obj : originalGroup) {
            groupMembers.add(obj);
        }

        GraphicObjectSingleton.getInstance().remove(id);
        System.out.printf("Ungrouped id %d\n", id);
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectSingleton.getInstance().add(originalGroup);
        System.out.printf("Undid ungrouping of id %d\n", id);
        return true;
    }
}