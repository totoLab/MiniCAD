package is.interpreter;

public class Pos implements ExpressionIF {

    private PosFloat x, y;

    public Pos(PosFloat x, PosFloat y) {
        this.x = new PosFloat(x.getValue());
        this.y = new PosFloat(y.getValue());
    }

    public PosFloat getX() {
        return x;
    }

    public PosFloat getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public CustomResult interpret(String input) {
        return new CustomResult();
    }
}
