package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.visitor.MoveVisitor;

import java.awt.geom.Point2D;

public class MoveCommand implements Command {

	protected final Point2D oldPos;

	protected final Point2D newPos;

	protected final AbstractGraphicObject object;
	protected final MoveVisitor visitor;

	public MoveCommand(GraphicObject go, Point2D pos) {
		oldPos = go.getPosition();
		newPos = pos;
		this.object = (AbstractGraphicObject) go;
		this.visitor = new MoveVisitor();
	}

	@Override
	public boolean doIt() {
		visitor.setPosition(newPos);
		object.accept(visitor);
		System.out.printf("%s with id %d moved to %s\n", object.getType(), object.getId(), newPos);
		return true;
	}

	@Override
	public boolean undoIt() {
		visitor.setPosition(oldPos);
		object.accept(visitor);
		return true;
	}

}
