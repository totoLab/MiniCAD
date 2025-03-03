package interpreter;

import java.beans.Expression;

public class Move implements ExpressionIF {

    private long id;
    private Pos pos;

    public Move(long id, Pos pos) {
        this.id = id;
        this.pos = new Pos(pos.getX(), pos.getY());
    }

    public long getId() {
        return id;
    }

    public Pos getPos() {
        return pos;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
