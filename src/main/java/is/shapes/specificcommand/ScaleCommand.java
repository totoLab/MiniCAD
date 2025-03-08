package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.visitor.ScaleVisitor;

public class ScaleCommand implements Command {
	
	private final AbstractGraphicObject object;
	private final double factor;
	private final ScaleVisitor visitor;

	public ScaleCommand(GraphicObject obj, double factor) {
		object = (AbstractGraphicObject) obj;
		this.factor = factor;
		this.visitor = new ScaleVisitor();
	}

	@Override
	public boolean doIt() {
		visitor.setFactor(factor);
		object.accept(visitor);
		System.out.printf("%s with id %d scaled with a factor of %f\n",
				object.getType(), (object).getId(), factor);
		return true;
	}

	@Override
	public boolean undoIt() {
		visitor.setFactor(1.0 / factor);
		object.accept(visitor);
		return true;
	}

}
