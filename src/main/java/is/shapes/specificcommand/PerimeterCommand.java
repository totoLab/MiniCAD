package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.base.Symbols;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.visitor.PerimeterVisitor;

import java.util.ArrayList;
import java.util.List;

public class PerimeterCommand implements Command {

    private List<AbstractGraphicObject> objects = new ArrayList<>();
    private GraphicObjectSingleton singleton = GraphicObjectSingleton.getInstance();
    public PerimeterCommand(long id) {
        objects.add(singleton.getById(id));
    }

    public PerimeterCommand(Symbols symbol) {
        List<AbstractGraphicObject> objs;
        switch (symbol) {
            case ALL -> objs = singleton.getAll();
            default -> objs = singleton.getByType(symbol.name());
        }
        this.objects.addAll(objs);
    }

    @Override
    public boolean doIt() {
        PerimeterVisitor perimeterVisitor = new PerimeterVisitor();
        double perimeter = 0;

        for (AbstractGraphicObject object : objects) {
            perimeter += object.accept(perimeterVisitor);
        }

        System.out.printf("Perimeter = %f\n", perimeter);
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
