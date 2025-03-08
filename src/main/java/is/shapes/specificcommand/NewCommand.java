package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.base.Pos;
import is.interpreter.base.Shape;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectFactory;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.view.GraphicObjectPanel;

public class NewCommand implements Command {

	private final GraphicObjectPanel panel;
	private final GraphicObject go;

	public NewCommand(GraphicObjectPanel panel, Shape type, Pos position) {
		this.go = GraphicObjectFactory.createGraphicObject(type, position);
		this.panel = panel;
		
	}

	@Override
	public boolean doIt() {
		GraphicObjectSingleton.getInstance().add((AbstractGraphicObject) go);
		System.out.printf("New %s created with id %d\n", go.getType(), ((AbstractGraphicObject) go).getId());
		panel.add(go);
		return true;
	}

	@Override
	public boolean undoIt() {
		GraphicObjectSingleton.getInstance().remove(((AbstractGraphicObject) go).getId());
		panel.remove(go);
		return true;
	}

}
