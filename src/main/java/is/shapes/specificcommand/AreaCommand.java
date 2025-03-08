package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.base.Symbols;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.visitor.AreaVisitor;

import java.util.ArrayList;
import java.util.List;

public class AreaCommand implements Command {

    private List<AbstractGraphicObject> objects = new ArrayList<>();
    private GraphicObjectSingleton singleton = GraphicObjectSingleton.getInstance();
    public AreaCommand(long id) {
        objects.add(singleton.getById(id));
    }

    public AreaCommand(Symbols symbol) {
        List<AbstractGraphicObject> objs;
        switch (symbol) {
            case ALL -> objs = singleton.getAll();
            default -> objs = singleton.getByType(symbol.name());
        }
        this.objects.addAll(objs);
    }

    @Override
    public boolean doIt() {
        AreaVisitor areaVisitor = new AreaVisitor();
        double totalArea = 0;

        for (AbstractGraphicObject object : objects) {
            totalArea += object.accept(areaVisitor);
        }

        System.out.printf("Area = %f\n", totalArea);
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
