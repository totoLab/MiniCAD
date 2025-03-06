package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

import java.util.ArrayList;
import java.util.List;

public class Group implements ExpressionIF {

    private List<Long> ids;

    public Group(List<Long> ids) {
        this.ids = new ArrayList<>(ids);
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {

    }
}
