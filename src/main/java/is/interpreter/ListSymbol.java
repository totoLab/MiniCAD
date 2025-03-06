package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class ListSymbol extends List{

    Symbols symbol;

    public ListSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
    }
}
