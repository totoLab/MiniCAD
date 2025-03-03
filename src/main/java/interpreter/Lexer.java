package interpreter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Lexer {

    StreamTokenizer tokenizer;
    Symbols currentSymbol;

    String value;
    double numberValue;

    Lexer(Reader in) {
        tokenizer = new StreamTokenizer(in);
        tokenizer.resetSyntax();
        tokenizer.parseNumbers();
        tokenizer.eolIsSignificant(false);
        tokenizer.wordChars('a', 'z');
        tokenizer.wordChars('A', 'Z');
        tokenizer.wordChars('0', '9');
        tokenizer.whitespaceChars('\u0000', ' ');
        tokenizer.ordinaryChar('(');
        tokenizer.ordinaryChar(')');
        tokenizer.quoteChar('"');
    }

    private boolean isInteger(double number) {
        return number == Math.round(number);
    }

    public Symbols nextToken() {
        try {
            switch (tokenizer.nextToken()) {
                case StreamTokenizer.TT_EOF:
                    currentSymbol = Symbols.EOF;
                    break;
                case StreamTokenizer.TT_WORD:
                    String word = tokenizer.sval.toLowerCase();
                    switch (word) {
                        case "new": currentSymbol = Symbols.NEW; break;
                        case "del": currentSymbol = Symbols.DELETE; break;
                        case "mv": currentSymbol = Symbols.MOVE; break;
                        case "mvoff": currentSymbol = Symbols.OFFSET; break;
                        case "scale": currentSymbol = Symbols.SCALE; break;
                        case "ls": currentSymbol = Symbols.LIST; break;
                        case "all": currentSymbol = Symbols.ALL; break;
                        case "groups": currentSymbol = Symbols.GROUPS; break;
                        case "group": currentSymbol = Symbols.GROUP; break;
                        case "ungroup": currentSymbol = Symbols.UNGROUP; break;
                        case "area": currentSymbol = Symbols.AREA; break;
                        case "perimeter": currentSymbol = Symbols.PERIMETER; break;
                        case "circle": currentSymbol = Symbols.CIRCLE; break;
                        case "rectangle": currentSymbol = Symbols.RECTANGLE; break;
                        case "img": currentSymbol = Symbols.IMG; break;
                    }
                    break;
                case StreamTokenizer.TT_NUMBER:
                    numberValue = tokenizer.nval;
                    if (isInteger(numberValue)) {
                        currentSymbol = Symbols.INTEGER;
                    } else {
                        currentSymbol = Symbols.FLOAT;
                    }
                    break;
                case '(': currentSymbol = Symbols.OPEN_PARENTHESIS; break;
                case ')': currentSymbol = Symbols.CLOSE_PARENTHESIS; break;
                case ',': currentSymbol = Symbols.COMMA; break;
                case '"': currentSymbol = Symbols.QUOTE; value = tokenizer.sval; break;
                default : currentSymbol = Symbols.INVALID; break;
            }
        } catch (IOException e) {
            currentSymbol = Symbols.EOF;
        }
        return currentSymbol;
    }

    boolean canBeFloat(Symbols symbol) {
        return symbol.equals(Symbols.FLOAT) || symbol.equals(Symbols.INTEGER);
    }

    public String getValue() {
        if (canBeFloat(currentSymbol)) return numberValue + "";
        return value;
    }
}
