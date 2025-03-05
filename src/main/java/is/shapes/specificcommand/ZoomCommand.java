package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.GraphicObject;

public class ZoomCommand implements Command {
	
	private final GraphicObject object;
	private final double factor;

	public ZoomCommand(GraphicObject obj, double factor) {
		object = obj;
		this.factor = factor;
		
	}

	@Override
	public boolean doIt() {
		object.scale(factor);
		return true;
	}

	@Override
	public boolean undoIt() {
		object.scale(1.0 / factor);
		return true;
	}

}
