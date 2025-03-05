package is.interpreter;

public class Scale implements ExpressionIF {

    private long id;
    private double scale;

    public Scale(long id, double scale) {
        this.id = id;
        this.scale = scale;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
