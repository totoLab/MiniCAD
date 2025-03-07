package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObjectSingleton;

public class UngroupCommand implements Command {

    long id;

    public UngroupCommand(long id) {
        this.id = id;
    }

    @Override
    public boolean doIt() {
        GraphicObjectSingleton.getInstance().remove(id);
        System.out.printf("Removed group with id %d\n", id);

        return true;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
