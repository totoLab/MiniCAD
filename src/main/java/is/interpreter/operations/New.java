package is.interpreter.operations;


import is.command.Command;
import is.command.CommandHandler;
import is.interpreter.ExpressionIF;
import is.interpreter.base.Pos;
import is.interpreter.base.Shape;
import is.shapes.model.GraphicObjectSingleton;
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
    public void interpret(String input, CommandHandler commandHandler) {
        GraphicObjectPanel gpanel = GraphicObjectSingleton.getInstance().getPanel();
        Command command = new NewCommand(gpanel, type, position);
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
