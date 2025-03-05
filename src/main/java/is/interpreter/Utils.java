package is.interpreter;

public class Utils {

    static boolean canBeFloat(Symbols symbol) {
        return symbol.equals(Symbols.FLOAT) || symbol.equals(Symbols.INTEGER);
    }

    static boolean isInteger(double number) {
        return number == Math.round(number);
    }

}
