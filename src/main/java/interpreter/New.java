package interpreter;

public class New implements ExpressionIF {

    ExpressionIF type;
    ExpressionIF position;

    public New(ExpressionIF typeConstructor, Pos pos) {
        this.type = typeConstructor;
        this.position = pos;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
