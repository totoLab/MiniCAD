package is.interpreter;

import java.util.ArrayList;
import java.util.List;

public class Group implements ExpressionIF {

    private List<Long> ids;

    public Group(List<Long> ids) {
        this.ids = new ArrayList<>(ids);
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
