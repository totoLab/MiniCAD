package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;

public class ScaleCommand implements Command {
	
	private final GraphicObject object;
	private final double factor;

	public ScaleCommand(GraphicObject obj, double factor) {
		object = obj;
		this.factor = factor;
		
	}

	@Override
	public boolean doIt() {
		System.out.printf("%s with id %d scaled with a factor of %f\n", object.getType(), ((AbstractGraphicObject) object).getId(), factor);
		object.scale(factor);
		return true;
	}

	@Override
	public boolean undoIt() {
		object.scale(1.0 / factor);
		return true;
	}

}
