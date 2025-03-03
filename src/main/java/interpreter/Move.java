package interpreter;

import java.beans.Expression;

public class Move implements ExpressionIF {

    long id;
    Pos pos;

    public Move(long id, Pos pos) {
        this.id = id;
        this.pos = new Pos(pos.getX(), pos.getY());
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
