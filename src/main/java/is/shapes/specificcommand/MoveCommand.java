package is.shapes.specificcommand;

import is.command.Command;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObject;
import is.shapes.model.GraphicObjectSingleton;

import java.awt.geom.Point2D;

public class MoveCommand implements Command {

	protected final Point2D oldPos;

	protected final Point2D newPos;

	protected final GraphicObject object;
	
	public MoveCommand(GraphicObject go, Point2D pos) {
		oldPos = go.getPosition();
		newPos = pos;
		this.object = go;
	}

	@Override
	public boolean doIt() {
		AbstractGraphicObject obj = (AbstractGraphicObject) object;
		object.moveTo(newPos);
		System.out.printf("%s with id %d moved to %s\n", obj.getType(), obj.getId(), newPos);
		return true;
	}

	@Override
	public boolean undoIt() {
		object.moveTo(oldPos);
		
		return true;
	}

}
