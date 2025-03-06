package is.interpreter;

import is.shapes.view.GraphicObjectPanel;

public class PerimeterSymbol extends Perimeter {

    private Symbols symbol;

    public PerimeterSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public void interpret(String input, GraphicObjectPanel gpanel) {
    }
}
