package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
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
        GraphicObjectSingleton.getInstance().remove(go.getId());
        System.out.printf("%s with id %d removed\n", go.getType(), go.getId());
        panel.remove(go);
        return true;
    }

    @Override
    public boolean undoIt() {
        GraphicObjectSingleton.getInstance().remove(go.getId());
        panel.add(go);
        return true;
    }
}
