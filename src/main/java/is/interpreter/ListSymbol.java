package is.interpreter;

public class ListSymbol extends List{

    Symbols symbol;

    public ListSymbol(Symbols symbol) {
        this.symbol = symbol;
    }

    @Override
    public CustomResult interpret(String input) {
        return null;
    }
}
