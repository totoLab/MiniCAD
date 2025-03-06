package is.interpreter;

public class New implements ExpressionIF {

    Shape type;
    Pos position;

    public New(Shape typeConstructor, Pos pos) {
        this.type = typeConstructor;
        this.position = pos;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
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
