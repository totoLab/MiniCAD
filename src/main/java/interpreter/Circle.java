package interpreter;

public class Circle extends Shape {

    PosFloat radius;

    public Circle(PosFloat x) {
        this.radius = new PosFloat(x.getValue());
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Circle (%f)", radius.getValue());
    }
}
