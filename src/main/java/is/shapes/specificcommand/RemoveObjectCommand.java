package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;

public class RemoveObjectCommand implements Command {

    private final GraphicObjectPanel panel;
    private final AbstractGraphicObject go;

    public RemoveObjectCommand(GraphicObjectPanel gpanel, AbstractGraphicObject go) {
        this.panel = gpanel;
        this.go = go;
    }

    @Override
    public boolean doIt() {
        if (go instanceof GroupObject) {
            GroupObject group = (GroupObject) go;
            for (AbstractGraphicObject child : group) {
                GraphicObjectSingleton.getInstance().remove(child.getId());
                System.out.printf("%s with id %d removed\n", child.getType(), child.getId());
                panel.remove(child);
            }
        }
        GraphicObjectSingleton.getInstance().remove(go.getId());
        System.out.printf("%s with id %d removed\n", go.getType(), go.getId());
        panel.remove(go);
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectSingleton.getInstance().add(go);
        panel.add(go);
        return true;
    }
}
