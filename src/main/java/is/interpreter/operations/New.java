package is.interpreter.operations;


import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.base.Pos;
import is.interpreter.base.Shape;
import is.shapes.model.AbstractGraphicObject;
import is.shapes.model.GraphicObjectFactory;
import is.shapes.specificcommand.NewCommand;
import is.shapes.view.GraphicObjectPanel;

public class New implements ExpressionIF {

    Shape type;
    Pos position;

    public New(Shape typeConstructor, Pos pos) {
        this.type = typeConstructor;
        this.position = pos;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel, CommandHandler commandHandler) {
        AbstractGraphicObject obj = GraphicObjectFactory.createGraphicObject(type, position);
        Command command = new NewCommand(gpanel, obj);
        commandHandler.handle(command);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.toString());
        sb.append(" ");
        sb.append(position.toString());
        return sb.toString();
    }
}
