package interpreter;

public class Delete implements ExpressionIF {

    private long id;

    public Delete(long id) {
        this.id = id;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
