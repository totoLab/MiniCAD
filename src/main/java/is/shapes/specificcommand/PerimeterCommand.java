package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.Symbols;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.visitor.AreaCalculator;
import is.shapes.visitor.PerimeterCalculator;

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
        PerimeterCalculator perimeterCalculator = new PerimeterCalculator();
        double perimeter = 0;

        for (AbstractGraphicObject object : objects) {
            perimeter += object.accept(perimeterCalculator);
        }

        System.out.printf("Perimeter = %f\n", perimeter);
        return false;
    }

    @Override
    public boolean undoIt() {
        return false;
    }
}
