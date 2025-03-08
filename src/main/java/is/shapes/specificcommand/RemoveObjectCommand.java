package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.model.GroupObject;
import is.shapes.view.GraphicObjectPanel;
import is.shapes.visitor.RemoveVisitor;

import java.util.List;

public class RemoveObjectCommand implements Command {

    private final GraphicObjectPanel panel;
    private final AbstractGraphicObject go;
    private final RemoveVisitor visitor;
    public RemoveObjectCommand(GraphicObjectPanel gpanel, AbstractGraphicObject go) {
        this.panel = gpanel;
        this.go = go;
        this.visitor = new RemoveVisitor(gpanel);
    }

    @Override
    public boolean doIt() {
        go.accept(visitor);
        return true;
    }

    @Override
    public boolean undoIt() {
        List<AbstractGraphicObject> removedObjects = visitor.getRemovedObjects();
        for (int i = removedObjects.size() - 1; i >= 0; i--) {
            AbstractGraphicObject obj = removedObjects.get(i);
            GraphicObjectSingleton.getInstance().add(obj);
            panel.add(obj);
            System.out.printf("Restored %s with id %d\n", obj.getType(), obj.getId());
        }
        return true;
    }
}
