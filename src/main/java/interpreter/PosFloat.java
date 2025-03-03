package interpreter;

public class PosFloat implements ExpressionIF {

    private float value;

    public PosFloat(float value) {
        if (value < 0) throw new SyntaxException("Negative value");
        else this.value = value;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue() + "";
    }

    @Override
    public CustomResult interpret(String input) {
        return new CustomResult();
    }
}
