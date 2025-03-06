package is.shapes.specificcommand;

import is.command.Command;
import is.interpreter.ExpressionIF;
import is.shapes.model.GraphicObject;
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
		panel.add(go);
		return true;
	}

	@Override
	public boolean undoIt() {
		panel.remove(go);
		return true;
	}

}
