package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExpressionIF;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectSingleton;
import is.shapes.view.GraphicObjectPanel;

public class NewObjectCmd implements Command {

	private final GraphicObjectPanel panel;
	private final GraphicObject go;

	public NewObjectCmd(GraphicObjectPanel panel, GraphicObject go) {
		
		this.panel = panel;
		this.go = go;
		
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
